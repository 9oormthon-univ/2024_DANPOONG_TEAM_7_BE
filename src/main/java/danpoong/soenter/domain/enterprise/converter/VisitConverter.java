package danpoong.soenter.domain.enterprise.converter;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.dto.VisitDTO.VisitResponse.GetVisitedEnterpriseResponse;
public class VisitConverter {
    public static GetVisitedEnterpriseResponse toVisitedEnterpriseResponse(Enterprise enterprise) {
        return GetVisitedEnterpriseResponse.builder()
                .enterpriseId(enterprise.getEnterpriseId())
                .enterpriseName(enterprise.getName())
                .socialPurpose(enterprise.getSocialPurpose())
                .city(enterprise.getCity())
                .district(enterprise.getDistrict())
                .website(enterprise.getWebsite())
                .latitude(enterprise.getLatitude())
                .longitude(enterprise.getLongitude())
                .build();
    }
}
