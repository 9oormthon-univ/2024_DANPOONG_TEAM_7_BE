package danpoong.soenter.domain.enterprise.converter;

import danpoong.soenter.domain.enterprise.dto.EnterpriseDTO.EnterpriseResponse.GetEnterpriseResponse;
import danpoong.soenter.domain.enterprise.entity.Enterprise;

public class EnterpriseConverter {
    public static GetEnterpriseResponse toEnterpriseResponse(Enterprise enterprise, int reviewCount) {
        return GetEnterpriseResponse.builder()
                .enterpriseId(enterprise.getEnterpriseId())
                .name(enterprise.getName())
                .region(enterprise.getRegion())
                .isCooperative(enterprise.getIs_cooperative())
                .certificationNumber(enterprise.getCertificationNumber())
                .socialPurpose(enterprise.getSocialPurpose())
                .type(enterprise.getType())
                .city(enterprise.getCity())
                .district(enterprise.getDistrict())
                .latitude(enterprise.getLatitude())
                .longitude(enterprise.getLongitude())
                .website(enterprise.getWebsite())
                .certificationYear(enterprise.getCertificationYear())
                .reviewCount(reviewCount)
                .build();
    }
}
