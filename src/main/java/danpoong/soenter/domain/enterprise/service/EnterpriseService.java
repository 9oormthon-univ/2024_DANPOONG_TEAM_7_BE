package danpoong.soenter.domain.enterprise.service;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Region;
import danpoong.soenter.domain.enterprise.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    public ApiResponse<List<Enterprise>> getEnterprisesByRegion(Region region) {
        try {
            List<Enterprise> enterprises = enterpriseRepository.findAllByRegion(region);
            return ApiResponse.onSuccess(enterprises);
        } catch (Exception e) {
            return ApiResponse.onFailure("500", "지역별 기업 정보를 가져오는 데 실패했습니다.", null);
        }
    }
}