package danpoong.soenter.domain.enterprise.service;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.enterprise.dto.VisitConverter;
import danpoong.soenter.domain.enterprise.dto.VisitDTO.VisitResponse.GetVisitedEnterpriseResponse;
import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Region;
import danpoong.soenter.domain.enterprise.entity.Visit;
import danpoong.soenter.domain.enterprise.repository.EnterpriseRepository;
import danpoong.soenter.domain.enterprise.repository.VisitRepository;
import danpoong.soenter.domain.review.converter.ReviewConverter;
import danpoong.soenter.domain.user.entity.User;
import danpoong.soenter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final UserRepository userRepository;
    private final VisitRepository visitRepository;

    public ApiResponse<List<Enterprise>> getEnterprisesByRegion(Region region) {
        try {
            List<Enterprise> enterprises = enterpriseRepository.findAllByRegion(region);
            return ApiResponse.onSuccess(enterprises);
        } catch (Exception e) {
            return ApiResponse.onFailure("500", "지역별 기업 정보를 가져오는 데 실패했습니다.", null);
        }
    }
    @Transactional(readOnly = true)
    public List<GetVisitedEnterpriseResponse> getVisitedEnterprises(String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        return visitRepository.findByUser(user).stream()
                .map(Visit::getEnterprise)  // Visit에서 Enterprise 추출
                .distinct()  // 동일한 Enterprise는 한 번만 포함
                .map(VisitConverter::toVisitedEnterpriseResponse)
                .collect(Collectors.toList());
    }
}