package capstone.market.post_dto;

import capstone.market.domain.*;
import lombok.Data;

import java.util.List;

@Data
public class AddPostRequest {
    private String item_name; // 상품명
    private String title;
    private Long user_id;
    private CategoryType category;
    private String content;
    //        private String time;
    private Integer price;

    private DepartmentType department;
    private LocationType locationType;
    private String location_text;
//        private String image_file_name;
    private List<String> images;

    private CollegeType college;


    private TrackType track;
}