package capstone.market.profile_dto;

import capstone.market.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SearchFilterDto2 {
    /**
     카테고리,조회수,좋아요,최신순,키워드
     **/

    List<String> categories = new ArrayList<>();

    List<String> locations = new ArrayList<>();


    List<String> departments = new ArrayList<>();


    String track;

    String collegeType;


    String sort;

    String keyword;

    Integer page = 0;
    Integer size = 20;



}