package danpoong.soenter.domain.enterprise.dto;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.dto.VisitDTO.VisitResponse.GetVisitedEnterpriseResponse;
public class VisitConverter {
    public static GetVisitedEnterpriseResponse toVisitedEnterpriseResponse(Enterprise enterprise) {
        return GetVisitedEnterpriseResponse.builder()
                .enterpriseId(enterprise.getEnterpriseId())
                .enterpriseName(enterprise.getName())
                .city(enterprise.getCity())
                .district(enterprise.getDistrict())
                .website(enterprise.getWebsite())
                .build();
    }
}
