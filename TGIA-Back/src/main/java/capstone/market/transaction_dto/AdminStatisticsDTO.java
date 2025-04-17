package capstone.market.transaction_dto;

import lombok.Data;

import java.util.Map;

@Data
public class AdminStatisticsDTO {

    /**
     * 1. 총 거래 갯수 + 회원 수
     */
    private Long totalPosts; // 총 게시글 갯수
    private Long totalTransactions; // 총 거래 갯수

    private Long totalUsers; // 총 회원 수


    /**
     * 2. 총 거래 갯수(카테고리별)
     */
    private Long totalTransactions_도서;
    private Long totalTransactions_필기구;
    private Long totalTransactions_생활가전;
    private  Long totalTransactions_패션의류;
    private Long totalTransactions_전자기기;
    private Long totalTransactions_부기굿즈;
    private  Long totalTransactions_뷰티미용;

    /**
     * 3. 총 게시글 갯수(카테고리별)
     */
    private Long totalPost_도서;
    private Long totalPost_필기구;
    private Long totalPost_생활가전;
    private Long totalPost_패션의류;
    private Long totalPost_전자기기;
    private Long totalPost_부기굿즈;
    private Long totalPost_뷰티미용;


    /**
     * 4. 기간 별 거래량
     */
    private Long dailyTransactions; // 하루동안 거래량
    private Long weeklyTransactions; // 한주동안 거래량
    private Long monthlyTransactions; // 한달동안 거래량
    private Long yearlyTransactions;  // 일년동안 거래량


    /**
     * 5. 1~12월 각 카테고리별 게시물 갯수
     */
    private Map<String, Map<String, Long>> monthlyPostCountsByCategory;
    /**
     * 6. 1~12월 각 카테고리별 거래량
     */
    private Map<String, Map<String, Long>> monthlyTransactionCountsByCategory;


    /**
     * 7. 거래 총 금액 , 카테고리 별 총 금액
     */

    private Long totalTransactionsPrice; // 거래 총 금액
    private Long totalTransactionsPrice_도서;
    private Long totalTransactionsPrice_필기구;
    private Long totalTransactionsPrice_생활가전;
    private  Long totalTransactionsPrice_패션의류;
    private Long totalTransactionsPrice_전자기기;
    private Long totalTransactionsPrice_부기굿즈;
    private  Long totalTransactionsPrice_뷰티미용;



    /**
     * 6. 1~12월  월 별 포스트 갯수 , 거래량
     */
    private Map<String, Long> monthlyPostCounts_전체;

    private Map<String, Long> monthlyTransactionCounts_전체;

}

//테스트 // test