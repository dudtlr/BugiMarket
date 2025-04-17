package capstone.market.profile_dto;


import lombok.Data;

@Data
public class EvaluationDto {

    //구매한 사람 = 나
    private Long purchase_userId;
    // 판매한사람 = 상대방 (내가 평가 할 사람)
    private Long sell_userId;

    //평가 점수
    private Integer evaluation;
}
