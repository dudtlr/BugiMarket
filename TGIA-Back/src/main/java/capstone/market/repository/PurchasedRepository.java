package capstone.market.repository;

import capstone.market.domain.Post;
import capstone.market.domain.Purchased;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {

}
