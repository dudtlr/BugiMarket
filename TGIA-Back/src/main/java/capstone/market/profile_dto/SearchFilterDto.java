package capstone.market.profile_dto;

import capstone.market.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchFilterDto {
    /**
     카테고리,조회수,좋아요,최신순,키워드
    **/

    List<CategoryType> categories;

    List<LocationType> locations;


    List<DepartmentType> departments;


    TrackType track;

    CollegeType collegeType;

    Integer views;
    Integer likes;
    String sort;
    String keyword;

    Integer page = 0;
    Integer size = 20;

    Integer ys=7;

}