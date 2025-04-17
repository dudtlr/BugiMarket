package capstone.market.repository;

import capstone.market.domain.*;
import capstone.market.profile_dto.BoardDetailDto;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.profile_dto.SearchFilterDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
@Transactional(readOnly = true)
@Repository
public class BoardRepository {

    private final JPAQueryFactory queryFactory;
    private static final Logger logger = LoggerFactory.getLogger(BoardRepository.class);



    //@Cacheable("searchResults")
    public List<BoardDetailDto> searchFilterWithPaging(SearchFilterDto searchFilterDto) {


        //long startTime = System.nanoTime(); // 시작 시간 기록


        QBoard board = QBoard.board;

        BooleanBuilder whereBuilder = new BooleanBuilder();
        Pageable pageable = PageRequest.of(searchFilterDto.getPage(), searchFilterDto.getSize());



        if (searchFilterDto.getCollegeType() != null && !searchFilterDto.getCollegeType().toString().isEmpty()) {
            whereBuilder.and(board.collegeType.eq(searchFilterDto.getCollegeType()));
        }


        if (searchFilterDto.getCategories() != null && !searchFilterDto.getCategories().isEmpty()) {
            BooleanExpression[] categoryExpressions = searchFilterDto.getCategories().stream()
                    .map(board.categoryType::eq)
                    .toArray(BooleanExpression[]::new);
            whereBuilder.andAnyOf(categoryExpressions);
        }






        //1. %검색어% => 인덱스 불가 => full scan => 성능 한계
//
//                if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
//                    whereBuilder.and(post.post_title.contains(searchFilterDto.getKeyword())
//                            .or(post.post_text.contains(searchFilterDto.getKeyword())));
//                }
//
        if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
            whereBuilder.and(board.post_title.contains(searchFilterDto.getKeyword()));
        }

        //2. 검색어% => 인덱스 사용 가능 but 기능 한계
        // if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
        //                    whereBuilder.and(post.post_title.startsWith(searchFilterDto.getKeyword())
        //                            .or(post.post_text.startsWith(searchFilterDto.getKeyword())));
        //                }


        //3. mysql - full text search => 인덱스 사용 가능하면서 기능 보완

//                if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
//
//                    NumberTemplate booleanTemplate= Expressions.numberTemplate(Double.class,
//                            "function('match',{0},{1})",board.post_title,searchFilterDto.getKeyword());
//
//                   whereBuilder.and(booleanTemplate.gt(0));
//                }




        if (searchFilterDto.getLocations() != null && !searchFilterDto.getLocations().isEmpty()) {
            BooleanExpression[] categoryExpressions2 = searchFilterDto.getLocations().stream()
                    .map(board.locationType::eq)
                    .toArray(BooleanExpression[]::new);
            whereBuilder.andAnyOf(categoryExpressions2);
        }



        if (searchFilterDto.getDepartments() != null && !searchFilterDto.getDepartments().isEmpty()) {
            BooleanExpression[] categoryExpressions3 = searchFilterDto.getDepartments().stream()
                    .map(board.departmentType::eq)
                    .toArray(BooleanExpression[]::new);
            whereBuilder.andAnyOf(categoryExpressions3);
        }



        //BooleanBuilder finalWhere = whereBuilder.and(post.purchased.isNull());
        //whereBuilder.and(post.purchased.isNull());

        //whereBuilder.and(post.status.eq(StatusType.판매중));

        OrderSpecifier<?> order;
        if (searchFilterDto.getSort() != null && searchFilterDto.getSort().equalsIgnoreCase("views")) {
            order = board.createdDate.desc();
        } else if (searchFilterDto.getSort() != null && searchFilterDto.getSort().equalsIgnoreCase("likes")) {
            order = board.createdDate.desc();
        } else {
            order = board.createdDate.desc();
        }





        // 페이징 처리
        //long page_number = pageable.getOffset();

        long page_number = searchFilterDto.getPage();
        int page_size = pageable.getPageSize();

        long queryStartTime = System.nanoTime();

        List<Board> filteredBoards = queryFactory
                .selectFrom(board)
                //.where(finalWhere)  // 판매되지 않은 게시글 필터링
                .where(whereBuilder)
                .orderBy(order)
                .offset(page_number * page_size)
                .limit(page_size)  // size 만큼의 데이터를 가져옴
                .fetch();

//        long queryEndTime = System.nanoTime();
//        logger.info("Database query execution time: {} ms", (queryEndTime - queryStartTime) / 1_000_000);
//
//        long mappingStartTime = System.nanoTime();



        List<BoardDetailDto> SearchBoards = filteredBoards.stream()
                .map(b -> new BoardDetailDto(b))
                .collect(Collectors.toList());

//        long mappingEndTime = System.nanoTime();
//        logger.info("Data mapping execution time: {} ms", (mappingEndTime - mappingStartTime) / 1_000_000);
//
//        long endTime = System.nanoTime(); // 전체 메서드 종료 시간
//        logger.info("searchFilterWithPaging execution time: {} ms", (endTime - startTime) / 1_000_000);



        return SearchBoards;

    }





}