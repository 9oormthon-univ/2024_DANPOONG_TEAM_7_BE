package danpoong.soenter.domain.review.repository;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.review.entity.Review;
import danpoong.soenter.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
    List<Review> findByEnterprise(Enterprise enterprise);
    int countByEnterprise_EnterpriseId(Long enterpriseId);
    long countByEnterprise(Enterprise enterprise);
}