package capstone.market.repository;

import capstone.market.domain.PurchaseReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseReviewJpaRepository extends JpaRepository<PurchaseReview,Long> {
}
