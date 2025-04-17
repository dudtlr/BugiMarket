package capstone.market.es.repository;

import capstone.market.domain.BoardDocumentDto;
import capstone.market.profile_dto.SearchFilterDto2;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardDocumentRepository {

    private final RestHighLevelClient elasticsearchClient;

    public List<BoardDocumentDto> search(SearchFilterDto2 condition) throws IOException {
        SearchRequest searchRequest = new SearchRequest("boards");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();


        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        where(boolQuery,
                eqCollegeType(condition.getCollegeType()),
                eqCategoryType(condition.getCategories()),
                eqLocationType(condition.getLocations()),
                eqDepartmentType(condition.getDepartments()),
                eqKeyword(condition.getKeyword())
        );
        sourceBuilder.query(boolQuery);


        sourceBuilder.sort("createdDate", SortOrder.DESC);


        int page = condition.getPage();
        int size = condition.getSize();
        sourceBuilder.from(page * size);
        sourceBuilder.size(size);

        searchRequest.source(sourceBuilder);
        SearchResponse response = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

        return parseResponse(response);
    }


    private void where(BoolQueryBuilder builder, QueryBuilder... queries) {
        for (QueryBuilder query : queries) {
            if (query != null) builder.filter(query);
        }
    }


    private QueryBuilder eqCollegeType(Object collegeType) {
        return collegeType != null ? QueryBuilders.termQuery("collegeType", collegeType) : null;
    }

    private QueryBuilder eqCategoryType(List<String> categories) {
        return (categories != null && !categories.isEmpty())
                ? QueryBuilders.termsQuery("categoryType", categories)
                : null;
    }

    private QueryBuilder eqLocationType(List<String> locations) {
        return (locations != null && !locations.isEmpty())
                ? QueryBuilders.termsQuery("locationType", locations)
                : null;
    }

    private QueryBuilder eqDepartmentType(List<String> departments) {
        return (departments != null && !departments.isEmpty())
                ? QueryBuilders.termsQuery("departmentType", departments)
                : null;
    }

    private QueryBuilder eqKeyword(String keyword) {
        return (keyword != null && !keyword.isBlank())
                ? QueryBuilders.boolQuery().should(QueryBuilders.matchPhrasePrefixQuery("postTitle", keyword))
                : null;
    }


    private List<BoardDocumentDto> parseResponse(SearchResponse response) {
        return Arrays.stream(response.getHits().getHits())
                .map(hit -> mapToDto(hit.getSourceAsMap()))
                .collect(Collectors.toList());
    }

    private BoardDocumentDto mapToDto(Map<String, Object> source) {
        BoardDocumentDto dto = new BoardDocumentDto();
        dto.setId((String) source.get("id"));
        dto.setPostTitle((String) source.get("postTitle"));
        dto.setCategoryType((String) source.get("categoryType"));
        dto.setDepartmentType((String) source.get("departmentType"));
        dto.setCollegeType((String) source.get("collegeType"));
        dto.setLocationType((String) source.get("locationType"));
        dto.setCreatedDate((String) source.get("createdDate"));
        dto.setModifiedDate((String) source.get("modifiedDate"));
        return dto;
    }
}
