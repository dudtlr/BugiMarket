package capstone.market.repository;

import capstone.market.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartMentJpaRepository extends JpaRepository<Department,Long> {

}
