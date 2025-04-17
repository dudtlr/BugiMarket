package capstone.market.manner_dto;

import lombok.Data;

@Data
public class MannerCheckListDTO {
    private Boolean goodPrice;
    private Boolean goodTime;
    private Boolean fastResponse;
    private Boolean badQuality;
    private Boolean noResponse;
    private Boolean reDealing;
    // 구매자, 판매자 id
    private Long buyerId;
    private Long sellerId;
    private Long post_id;
    private Integer mannerScore;

    public MannerCheckListDTO() {
    }

    public MannerCheckListDTO(Boolean goodPrice, Boolean goodTime, Boolean fastResponse, Boolean badQuality, Boolean noResponse, Boolean reDealing, Long buyerId, Long sellerId, Long post_id, Integer mannerScore) {
        this.goodPrice = goodPrice;
        this.goodTime = goodTime;
        this.fastResponse = fastResponse;
        this.badQuality = badQuality;
        this.noResponse = noResponse;
        this.reDealing = reDealing;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.post_id = post_id;
        this.mannerScore = mannerScore;
    }
}
