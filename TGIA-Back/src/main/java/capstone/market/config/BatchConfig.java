package capstone.market.config;

import capstone.market.domain.*;

import capstone.market.repository.MemberRepository;
import capstone.market.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JdbcTemplate jdbcTemplate;
    private final RestHighLevelClient client;
    private static final String INSERT_POST_SQL = "INSERT INTO board (post_title, category_type, department_type, college_type, location_type, created_date, modified_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String[] salePhrases = {"팔아요", "팔께요", "팔아 버립니다", "사주세요", "싸게 드려요"};
    private static final String[] randomTitles = {"세련된 티셔츠", "스타일리시한 자켓", "귀여운 부기인형", "베스트셀러 도서", "편리한 가전제품"};
    private static final CategoryType[] categories = CategoryType.values();
    private static final DepartmentType[] departments = DepartmentType.values();
    private static final CollegeType[] colleges = CollegeType.values();
    private static final LocationType[] locations = LocationType.values();

    @Bean
    public Job dummyDataJob() {
        return jobBuilderFactory.get("dummyDataJob")
                .incrementer(new RunIdIncrementer())
                .start(dummyDataStep())
                .build();
    }



    @Bean
    public Step dummyDataStep() {
        return stepBuilderFactory.get("dummyDataStep")
                .<Board, Board>chunk(200)
                .reader(dummyDataReader(null))
                .processor(dummyDataProcessor())
                .writer(dummyDataWriter())
                .build();
    }




    @Bean
    @StepScope
    public ListItemReader<Board> dummyDataReader(@Value("#{jobParameters['count']}") Long count) {
        List<Board> dummyDataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Board dummyBoard = new Board();
            dummyBoard.setCollegeType(colleges[random.nextInt(colleges.length)]);
            dummyBoard.setCategoryType(categories[random.nextInt(categories.length)]);
            dummyBoard.setPost_title(randomTitles[random.nextInt(randomTitles.length)] + " " + salePhrases[random.nextInt(salePhrases.length)]);
            dummyBoard.setDepartmentType(departments[random.nextInt(departments.length)]);
            dummyBoard.setLocationType(locations[random.nextInt(locations.length)]);
            dummyBoard.setCreatedDate(LocalDateTime.now());
            dummyBoard.setModifiedDate(LocalDateTime.now());
            dummyDataList.add(dummyBoard);
        }
        return new ListItemReader<>(dummyDataList);
    }

    @Bean
    public ItemProcessor<Board, Board> dummyDataProcessor() {
        return board -> board;
    }

    @Bean
    public ItemWriter<Board> dummyDataWriter() {
        return boards -> {
            batchInsertPosts((List<Board>) boards);
            List<BoardDocument> boardDocuments = boards.stream()
                    .map(BoardDocument::new)
                    .collect(Collectors.toList());
            bulkInsertToElasticsearch(boardDocuments);
        };
    }

    private void batchInsertPosts(List<Board> boards) {
        jdbcTemplate.batchUpdate(INSERT_POST_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Board board = boards.get(i);
                ps.setString(1, board.getPost_title());
                ps.setString(2, board.getCategoryType().name());  // Enum as STRING
                ps.setString(3, board.getDepartmentType().name()); // Enum as STRING
                ps.setString(4, board.getCollegeType().name());    // Enum as STRING
                ps.setString(5, board.getLocationType().name());   // Enum as STRING
                ps.setObject(6, board.getCreatedDate());
                ps.setObject(7, board.getModifiedDate());
            }

            @Override
            public int getBatchSize() {
                return boards.size();
            }
        });
    }

    private void bulkInsertToElasticsearch(List<BoardDocument> boardDocuments) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            for (BoardDocument boardDocument : boardDocuments) {
                bulkRequest.add(new IndexRequest("boards")
                        .id(boardDocument.getId())
                        .source("postTitle", boardDocument.getPostTitle(),
                                "categoryType", boardDocument.getCategoryType(),
                                "departmentType", boardDocument.getDepartmentType(),
                                "collegeType", boardDocument.getCollegeType(),
                                "locationType", boardDocument.getLocationType(),
                                 "createdDate", boardDocument.getCreatedDate(),
                                "modifiedDate", boardDocument.getModifiedDate()));
            }

            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()) {
                System.out.println("Bulk insertion had failures: " + bulkResponse.buildFailureMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
