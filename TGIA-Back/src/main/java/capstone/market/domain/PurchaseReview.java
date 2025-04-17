package capstone.market.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class PurchaseReview extends BaseEntity {

    @Id
    @GeneratedValue
    private Long purchasereview_id;

    private String review;

    private Long seller_id;

    private Long buyer_id;

    private String buyer_username;

    private String user_type;

    private String imageFilename;


}
