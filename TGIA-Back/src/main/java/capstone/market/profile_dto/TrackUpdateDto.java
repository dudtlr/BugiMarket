package capstone.market.profile_dto;

import capstone.market.domain.DepartmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackUpdateDto {
    // 유저아이디
    private Long userId;
    // 1트랙이면 숫자 1 2트랙이면 숫자 2
//    private Integer trackNumber;
    private Integer trackNumber;
    //트랙 아이디
    private Long trackId;
    private String trackname;

    private DepartmentType departmentType;
}

