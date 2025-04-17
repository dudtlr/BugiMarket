package capstone.market.kakao_dto;


import capstone.market.domain.CategoryType;
import capstone.market.domain.DepartmentType;
import capstone.market.domain.LocationType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KakaoPayDto {


    private Long post_id;
    private Long user_id;

    private Long buyer_id;

    private Integer price;
    private String item_name;






}
