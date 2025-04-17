package capstone.market.repository;

import capstone.market.domain.SearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Long> {
    List<SearchKeyword> findTop10ByOrderBySearchCountDesc();
    SearchKeyword findByKeyword(String keyword);
}