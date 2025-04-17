package capstone.market.transaction_dto;

import capstone.market.domain.PurchaseReview;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
public class PurchaseReviewDTO {
    private Long seller_id;
    private Long buyer_id;
    private Long purchasereview_id;
    private String review;
    private String buyer_username;
    private String user_type;
    private String imageFilename;

    private Long post_id;

    public PurchaseReviewDTO(PurchaseReview purchaseReview) {
        this.seller_id = purchaseReview.getSeller_id();
        this.buyer_id = purchaseReview.getBuyer_id();
        this.purchasereview_id = getPurchasereview_id();
        this.review = purchaseReview.getReview();
        this.buyer_username = purchaseReview.getBuyer_username();
        this.user_type = purchaseReview.getUser_type();
        this.imageFilename = purchaseReview.getImageFilename();
    }


}
