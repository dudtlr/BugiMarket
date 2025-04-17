package capstone.market.es.repository;

import capstone.market.es.dto.PostDocumentDTO;
import capstone.market.profile_dto.SearchFilterDto2;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostDocumentElasticsearchRepository {

    private final RestHighLevelClient elasticsearchClient;


    //@Cacheable("post_documents") // 레디스 캐싱을 위해 어노테이션 설정
    public List<PostDocumentDTO> search (SearchFilterDto2 searchFilterDto) throws IOException {
        SearchRequest searchRequest = new SearchRequest("post"); // Elasticsearch에서 사용되는 인덱스명
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();


        // 트랙
        if (searchFilterDto.getTrack() != null && !searchFilterDto.getTrack().toString().isEmpty()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("track", searchFilterDto.getTrack()));
        }

        // 대학
        if (searchFilterDto.getCollegeType() != null && !searchFilterDto.getCollegeType().toString().isEmpty()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("college", searchFilterDto.getCollegeType()));
        }

        // 카테고리 => list 여서 terms query
        if (searchFilterDto.getCategories() != null && !searchFilterDto.getCategories().isEmpty()) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("category", searchFilterDto.getCategories()));
        }


        // 거래 장소 => list 여서 terms query
        if (searchFilterDto.getLocations() != null && !searchFilterDto.getLocations().isEmpty()) {

            boolQueryBuilder.filter(QueryBuilders.termsQuery("locationType", searchFilterDto.getLocations()));


        }

        // 학부 => list 여서 terms query
        if (searchFilterDto.getDepartments() != null && !searchFilterDto.getDepartments().isEmpty()) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("department", searchFilterDto.getDepartments()));
        }



        //검색 필터링 조건에 키워드가 포함되어 있는 경우에만 실행되며, 제목 또는 내용 중 하나라도 키워드와 부분 일치하는 게시물을 반환
        if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
            BoolQueryBuilder textQueryBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchPhrasePrefixQuery("title", searchFilterDto.getKeyword()))
                    .should(QueryBuilders.matchPhrasePrefixQuery("text", searchFilterDto.getKeyword()));

            boolQueryBuilder.filter(textQueryBuilder);
        }





        // 게시글 검색 시 판매중인 게시글만 보여준다.

        boolQueryBuilder.filter(QueryBuilders.termQuery("statusType", "판매중"));

        searchSourceBuilder.query(boolQueryBuilder);

        // Sorting
        // 필터링 조건 중 sort에 담긴 글자에 따라 정렬의 방법이 달라짐 (내림차순)
        if ("views".equalsIgnoreCase(searchFilterDto.getSort())) {
            searchSourceBuilder.sort("views", SortOrder.DESC);
        } else if ("likes".equalsIgnoreCase(searchFilterDto.getSort())) {
            searchSourceBuilder.sort("likes", SortOrder.DESC);
        } else {
            searchSourceBuilder.sort("createdDate", SortOrder.DESC);
        }

        // Paging
        int page_number = searchFilterDto.getPage();
        int page_size = searchFilterDto.getSize();
        searchSourceBuilder.from(page_number * page_size);
        searchSourceBuilder.size(page_size);

        searchRequest.source(searchSourceBuilder);

        // Elasticsearch로부터 검색 결과 가져오기
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

        // Elasticsearch 응답을 PostDocument 객체의 리스트로 변환하여 반환

        return parseSearchResponse(searchResponse);

        //return searchResponse;

    }

    private List<PostDocumentDTO> parseSearchResponse(SearchResponse searchResponse) {
        return Arrays.stream(searchResponse.getHits().getHits())
                .map(hit -> {
                    // Elasticsearch 응답을 PostDocumentDTO로 변환
                    return mapToPostDocument(hit.getSourceAsMap());
                })
                .collect(Collectors.toList());
    }

    private PostDocumentDTO mapToPostDocument(Map<String, Object> source) {
        PostDocumentDTO postDocument = new PostDocumentDTO();

        postDocument.setPostId((Integer) source.get("post_id"));
        postDocument.setTitle((String) source.get("title"));
        postDocument.setMemberId((Integer) source.get("member_id"));
        postDocument.setCategory((String) source.get("category"));
        postDocument.setDepartment((String) source.get("department"));
        postDocument.setText((String) source.get("text"));
        postDocument.setPrice((Integer) source.get("price"));
        postDocument.setViews((Integer) source.get("views"));
        postDocument.setLikes((Integer) source.get("likes"));
        postDocument.setLocationType((String) source.get("locationType"));
        postDocument.setLocationText((String) source.get("location_text"));
        postDocument.setCreatedDate((String) source.get("createdDate"));
        postDocument.setModifiedDate((String) source.get("modifiedDate"));
        postDocument.setCollege((String) source.get("college"));
        postDocument.setImages((List<String>) source.get("images"));
        postDocument.setStatusType((String) source.get("statusType"));


        return postDocument;
    }
}
