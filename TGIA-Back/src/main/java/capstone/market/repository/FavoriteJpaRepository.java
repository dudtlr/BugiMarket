package capstone.market.repository;

import capstone.market.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteJpaRepository extends JpaRepository<Favorite,Long> {

}
