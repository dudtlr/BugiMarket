package capstone.market.repository;


import capstone.market.domain.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
@Transactional
@RequiredArgsConstructor
public class TransactionRepository {

    private final EntityManager em;



    /**
     * 0. 회원 수
     */

    public Long getTotalUsers(){
        String jpql = "SELECT COUNT(b) FROM Member b";
        return em.createQuery(jpql, Long.class)
                .getSingleResult();
    }


    /**
     * 1. 총 게시물 갯수
     */


    public Long getTotalPosts(){
        String jpql = "SELECT COUNT(p) FROM Post p";
        return em.createQuery(jpql, Long.class)
                .getSingleResult();
    }

    /**
     * 2. 총 거래 갯수
     */
    public Long getTotalTransactions(){
        String jpql = "SELECT COUNT(p) FROM Purchased p";
        return em.createQuery(jpql, Long.class)
                .getSingleResult();
    }

    /**
     * 3. 총 포스트 갯수(카테고리별)  도서, 필기구, 생활가전, 패션패션의류, 전자기기, 부기굿즈, 뷰티미용
     */
    public Long getTotalPosts_도서(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.도서)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_필기구(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.필기구)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_생활가전(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.생활가전)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_패션의류(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.패션의류)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_전자기기(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.전자기기)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_부기굿즈(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.부기굿즈)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_뷰티미용(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.뷰티미용)
                .getSingleResult();
        return count;

    }



    /**
     * 4. 총 거래 갯수(카테고리별) 도서, 필기구, 생활가전, 패션의류, 전자기기, 부기굿즈, 뷰티미용
     */

    public Long getTotalTransactions_도서(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.도서)
                .getSingleResult();
         return count;

    }


    public Long getTotalTransactions_필기구(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.필기구)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_생활가전(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.생활가전)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_패션의류(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.패션의류)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_전자기기(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.전자기기)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_부기굿즈(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.부기굿즈)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_뷰티미용(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.categoryType = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.뷰티미용)
                .getSingleResult();
        return count;

    }


    /**
     * 5.  기간별 거래량 ( 일 , 주 , 월, 년)
     */


    public Long getTransactionCountByPeriod(String period) {
        LocalDateTime startDate;
        LocalDateTime endDate;

        switch (period) {
            case "DAILY":
                startDate = LocalDateTime.now().with(LocalTime.MIN);
                endDate = LocalDateTime.now().with(LocalTime.MAX);
                break;
            case "WEEKLY":
                startDate = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
                endDate = LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).with(LocalTime.MAX);
                break;
            case "MONTHLY":
                startDate = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
                endDate = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
                break;
            case "YEARLY":
                startDate = LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
                endDate = LocalDateTime.now().with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
                break;
            default:
                throw new IllegalArgumentException("Invalid period");
        }

        String jpql = "SELECT COUNT(p) FROM Purchased p WHERE p.createdDate BETWEEN :startDate AND :endDate";
        return em.createQuery(jpql, Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getSingleResult();
    }



    /**
     * 6. 월별 카테고리별 포스트 조회
     */
//    public Map<String, Map<String, Long>> getMonthlyPostCountsByCategory(int year) {
//        Map<String, Map<String, Long>> monthlyTransactionCounts = new LinkedHashMap<>();
//
//        for (CategoryType category : CategoryType.values()) {
//            Map<String, Long> countsByMonth = new LinkedHashMap<>();
//
//            for (int month = 1; month <= 12; month++) {
//                YearMonth yearMonth = YearMonth.of(year, month);
//                String monthLabel = yearMonth.toString();
//
//                String jpql = "SELECT COUNT(p) " +
//                        "FROM Post p " +
//                        "WHERE p.category.category_type = :category " +
//                        "AND YEAR(p.createdDate) = :year " +
//                        "AND MONTH(p.createdDate) = :month";
//
//                Long count = em.createQuery(jpql, Long.class)
//                        .setParameter("category", category)
//                        .setParameter("year", year)
//                        .setParameter("month", month)
//                        .getSingleResult();
//
//                countsByMonth.put(monthLabel, count);
//            }
//
//            String categoryName = category.toString();
//            monthlyTransactionCounts.put(categoryName, countsByMonth);
//        }
//
//        return monthlyTransactionCounts;
//    }


    public Map<String, Map<String, Long>> getMonthlyPostCountsByCategory(int year) {
        Map<String, Map<String, Long>> monthlyTransactionCounts = new LinkedHashMap<>();
        Map<String, Long> totalCountsByMonth = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(year, month);
            String monthLabel = yearMonth.toString();

            String jpql = "SELECT COUNT(p) " +
                    "FROM Post p " +
                    "WHERE YEAR(p.createdDate) = :year " +
                    "AND MONTH(p.createdDate) = :month";

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getSingleResult();

            totalCountsByMonth.put(monthLabel, count);
        }

        monthlyTransactionCounts.put("전체", totalCountsByMonth);

        for (CategoryType category : CategoryType.values()) {
            Map<String, Long> countsByMonth = new LinkedHashMap<>();

            for (int month = 1; month <= 12; month++) {
                YearMonth yearMonth = YearMonth.of(year, month);
                String monthLabel = yearMonth.toString();

                String jpql = "SELECT COUNT(p) " +
                        "FROM Post p " +
                        "WHERE p.categoryType = :category " +
                        "AND YEAR(p.createdDate) = :year " +
                        "AND MONTH(p.createdDate) = :month";

                Long count = em.createQuery(jpql, Long.class)
                        .setParameter("category", category)
                        .setParameter("year", year)
                        .setParameter("month", month)
                        .getSingleResult();

                countsByMonth.put(monthLabel, count);
            }

            String categoryName = category.toString();
            monthlyTransactionCounts.put(categoryName, countsByMonth);
        }

        return monthlyTransactionCounts;
    }


    /**
     * 6. 월별 카테고리별 거래량 조회
     */
//    public Map<String, Map<String, Long>> getMonthlyTransactionCountsByCategory(int year) {
//        Map<String, Map<String, Long>> monthlyTransactionCounts = new LinkedHashMap<>();
//
//        for (CategoryType category : CategoryType.values()) {
//            Map<String, Long> countsByMonth = new LinkedHashMap<>();
//
//            for (int month = 1; month <= 12; month++) {
//                YearMonth yearMonth = YearMonth.of(year, month);
//                String monthLabel = yearMonth.toString();
//
//                String jpql = "SELECT COUNT(p) " +
//                        "FROM Post p " +
//                        "WHERE p.category.category_type = :category " +
//                        "AND YEAR(p.createdDate) = :year " +
//                        "AND p.purchased is not null " +
//                        "AND MONTH(p.createdDate) = :month";
//
//                Long count = em.createQuery(jpql, Long.class)
//                        .setParameter("category", category)
//                        .setParameter("year", year)
//                        .setParameter("month", month)
//                        .getSingleResult();
//
//                countsByMonth.put(monthLabel, count);
//            }
//
//            String categoryName = category.toString();
//            monthlyTransactionCounts.put(categoryName, countsByMonth);
//        }
//
//        return monthlyTransactionCounts;
//    }

    public Map<String, Map<String, Long>> getMonthlyTransactionCountsByCategory(int year) {
        Map<String, Map<String, Long>> monthlyTransactionCounts = new LinkedHashMap<>();
        Map<String, Long> totalCountsByMonth = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(year, month);
            String monthLabel = yearMonth.toString();

            String jpql = "SELECT COUNT(p) " +
                    "FROM Post p " +
                    "WHERE YEAR(p.createdDate) = :year and p.purchased is not null " +
                    "AND MONTH(p.createdDate) = :month";

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getSingleResult();

            totalCountsByMonth.put(monthLabel, count);
        }

        monthlyTransactionCounts.put("전체", totalCountsByMonth);

        for (CategoryType category : CategoryType.values()) {
            Map<String, Long> countsByMonth = new LinkedHashMap<>();

            for (int month = 1; month <= 12; month++) {
                YearMonth yearMonth = YearMonth.of(year, month);
                String monthLabel = yearMonth.toString();

                String jpql = "SELECT COUNT(p) " +
                        "FROM Post p " +
                        "WHERE p.categoryType = :category and p.purchased is not null " +
                        "AND YEAR(p.createdDate) = :year " +
                        "AND MONTH(p.createdDate) = :month";

                Long count = em.createQuery(jpql, Long.class)
                        .setParameter("category", category)
                        .setParameter("year", year)
                        .setParameter("month", month)
                        .getSingleResult();

                countsByMonth.put(monthLabel, count);
            }

            String categoryName = category.toString();
            monthlyTransactionCounts.put(categoryName, countsByMonth);
        }

        return monthlyTransactionCounts;
    }

    /**
     * 7. 거래 총 금액
     */

    public Long getTotalTransactionsPrice() {
        String jpql = "SELECT SUM(p.price) FROM Purchased p";
        return em.createQuery(jpql, Long.class)
                .getSingleResult();
    }




    /**
     * 8. 카테고리 별 총 금액
     */

    public Long getTotalTransactionsPriceByCategory(CategoryType category) {
        String jpql = "SELECT SUM(p.price) FROM Post p WHERE p.categoryType = :category and p.purchased is not null";

        Long totalAmount = em.createQuery(jpql, Long.class)
                .setParameter("category", category)
                .getSingleResult();

        return totalAmount != null ? totalAmount : 0L;
    }


    /**
     * 9. 월별 포스트 조회
     */


    public Map<String, Long> getMonthlyPostCounts() {
        int currentYear = Year.now().getValue();
        Map<String, Long> monthlyPostCounts = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(currentYear, month);
            String monthLabel = yearMonth.toString();

            String jpql = "SELECT COUNT(p) " +
                    "FROM Post p " +
                    "WHERE YEAR(p.createdDate) = :year " +
                    "AND MONTH(p.createdDate) = :month";

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("year", currentYear)
                    .setParameter("month", month)
                    .getSingleResult();

            monthlyPostCounts.put(monthLabel, count);
        }

        return monthlyPostCounts;
    }




    /**
     * 10. 월별 거래량 조회
     */

    public Map<String, Long> getMonthlyPurchasedCounts() {
        int currentYear = Year.now().getValue();
        Map<String, Long> monthlyPostCounts = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(currentYear, month);
            String monthLabel = yearMonth.toString();

            String jpql = "SELECT COUNT(p) " +
                    "FROM Post p " +
                    "WHERE YEAR(p.createdDate) = :year and p.purchased is not null " +
                    "AND MONTH(p.createdDate) = :month";

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("year", currentYear)
                    .setParameter("month", month)
                    .getSingleResult();

            monthlyPostCounts.put(monthLabel, count);
        }

        return monthlyPostCounts;
    }




}





