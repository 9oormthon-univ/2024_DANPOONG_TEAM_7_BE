package danpoong.soenter.domain.user.repository;

import danpoong.soenter.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("SELECT u.kakaoAccess FROM User u WHERE u.userId = :userId")
    String findKakaoAccessById(@Param("userId") Long userId);
}
