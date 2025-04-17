package capstone.market.repository;

import capstone.market.domain.Post;
import capstone.market.domain.PurchaseReview;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.transaction_dto.PurchaseReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional
public class PurchaseReviewRepository {

    private final EntityManager em;



    public List<PurchaseReviewDTO> findLatestPurchaseReviews(Long userId) {
        String jpql = "SELECT p FROM PurchaseReview p WHERE p.seller_id =:userId ORDER BY p.createdDate DESC";
        TypedQuery<PurchaseReview> query = em.createQuery(jpql, PurchaseReview.class);
        query.setParameter("userId", userId);
        query.setMaxResults(3); // 최대 4개의 결과만 반환하도록 설정
        List<PurchaseReview> resultList = query.getResultList();

        List<PurchaseReviewDTO> SearchPosts = resultList.stream().map(p -> new PurchaseReviewDTO(p))
                .collect(Collectors.toList());
        return SearchPosts;

    }


    public List<PurchaseReviewDTO> getAllPurchaseReview(Long userId) {
        String jpql = "SELECT p FROM PurchaseReview p WHERE p.seller_id =:userId ORDER BY p.createdDate DESC";
        TypedQuery<PurchaseReview> query = em.createQuery(jpql, PurchaseReview.class);
        query.setParameter("userId", userId);// 최대 4개의 결과만 반환하도록 설정
        List<PurchaseReview> resultList = query.getResultList();

        List<PurchaseReviewDTO> SearchPosts = resultList.stream().map(p -> new PurchaseReviewDTO(p))
                .collect(Collectors.toList());
        return SearchPosts;

    }


    public Long getPurchaseReviewCount(Long userId) {
        String jpql = "SELECT COUNT(p) FROM PurchaseReview p WHERE p.seller_id = :userId";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("userId", userId);
        Long result = query.getSingleResult();
        return result;
    }






}
