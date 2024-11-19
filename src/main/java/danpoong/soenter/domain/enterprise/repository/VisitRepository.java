package danpoong.soenter.domain.enterprise.repository;

import danpoong.soenter.domain.enterprise.entity.Visit;
import danpoong.soenter.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByUser(User user);
}