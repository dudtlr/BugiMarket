package capstone.market.searchKeyword_dto;

import capstone.market.domain.SearchKeyword;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class SearchKeywordDTO {


    private Long id;

    private String keyword;
    private Long searchCount;

    public SearchKeywordDTO(SearchKeyword searchKeyword) {
        this.id = searchKeyword.getId();
        this.keyword = searchKeyword.getKeyword();
        this.searchCount = searchKeyword.getSearchCount();
    }
}
