package danpoong.soenter.domain.likes.repository;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.likes.entity.Likes;
import danpoong.soenter.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByUser(User user);
    boolean existsByUserAndEnterprise(User user, Enterprise enterprise);
    void deleteByUserAndEnterprise(User user, Enterprise enterprise);
}