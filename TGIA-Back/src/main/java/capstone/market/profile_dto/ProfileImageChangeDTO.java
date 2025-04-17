package capstone.market.profile_dto;

import capstone.market.domain.Member;
import lombok.Data;

// 3월 17일
@Data
public class ProfileImageChangeDTO {
    private Long member_id;
    private String image_filename;
}
