package danpoong.soenter.domain.program.repository;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.program.entity.Program;
import danpoong.soenter.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByEnterprise(Enterprise enterprise);
    long countByUser(User user);
}

