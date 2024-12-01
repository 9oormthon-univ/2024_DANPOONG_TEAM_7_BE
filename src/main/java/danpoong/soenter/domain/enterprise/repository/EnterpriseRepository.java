package danpoong.soenter.domain.enterprise.repository;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    List<Enterprise> findAllByRegion(Region region);
    List<Enterprise> findAllByRegionAndCity(Region region, String city);
    Optional<Enterprise> findByBusinessRegistrationNumberAndRepresentativeNameAndName(
            String businessRegistrationNumber,
            String representativeName,
            String name
    );
}
