package capstone.market.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.FetchMode;
import org.springframework.lang.Nullable;

import javax.persistence.*;

// 구매 목록
@Entity
@Data
public class Purchased extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private Integer price;
    private String productName;
//    private Category category;

    private String buyer_username; // 따로입력
    private String seller_username; // 따로입력
    private String tid; // 결제 고유 번호
    private String payment_method_type; // 결제 수단
    private String item_name; // 상품명
    private int quantity; // 상품 수량
    private String approved_at; // 결제 승인 시간
    private String postTitle;


    /**
     * 유저 한명당 구매 수 있는게 여러가지
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    @Nullable
    private Member member;





    /**
     *
     */
//    @OneToOne
//    @JoinColumn(name = "post_id")
//    private Post post;

}

