package capstone.market.kakao_dto;

import lombok.Data;

@Data
public class KakaoReadyResponse {

    private String tid; // 결제 고유 번호
    private String next_redirect_mobile_url; // 모바일 웹일 경우 받는 결제페이지 url
    private String next_redirect_pc_url; // pc 웹일 경우 받는 결제 페이지
    private String created_at;
    private String next_redirect_app_url; // 모바일 앱 일 경우 받는 결제 페이지
    private String android_app_scheme;

    private String item_name; //상품 이름
    private Integer item_price ; //상품 가격
    private Long seller_id;  //판매자
    private Long buyer_id ; //구매자
    private Long post_id ;//게시글 기본 키
}