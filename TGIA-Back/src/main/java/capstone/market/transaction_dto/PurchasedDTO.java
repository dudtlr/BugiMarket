package capstone.market.transaction_dto;

import capstone.market.domain.Purchased;
import capstone.market.kakao_dto.AmountDto;
import capstone.market.kakao_dto.KakaoApproveResponse;
import lombok.Data;

@Data
public class PurchasedDTO {
    private Integer price; // 따로입력
    private String buyer_username; // 따로입력
    private String seller_username; // 따로입력
    private String tid; // 결제 고유 번호
    private String payment_method_type; // 결제 수단
    private String item_name; // 상품명
    private int quantity; // 상품 수량
    private String approved_at; // 결제 승인 시간
    private String postTitle;

    public PurchasedDTO(Purchased purchased) {
        this.price = purchased.getPrice();
        this.buyer_username = purchased.getBuyer_username();
        this.seller_username = purchased.getSeller_username();
        this.tid = purchased.getTid();
        this.payment_method_type = purchased.getPayment_method_type();
        this.item_name = purchased.getItem_name();
        this.quantity = purchased.getQuantity();
        this.approved_at = purchased.getApproved_at();
        this.postTitle = purchased.getPostTitle();
    }
}
