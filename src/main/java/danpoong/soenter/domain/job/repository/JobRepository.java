package danpoong.soenter.domain.job.repository;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.job.entity.Job;
import danpoong.soenter.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEnterprise(Enterprise enterprise);
    long countByUser(User user);
}
