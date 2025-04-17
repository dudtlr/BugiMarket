package capstone.market.repository;

import capstone.market.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDataJpa extends JpaRepository<Member, Long> {
}
