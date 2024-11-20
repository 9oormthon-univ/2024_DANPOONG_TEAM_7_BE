package danpoong.soenter.domain.likes.converter;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.likes.dto.LikesDTO.LikesResponse.GetLikeResponse;

public class LikesConverter {
    public static GetLikeResponse toGetLikeResponse(Enterprise enterprise) {
        return GetLikeResponse.builder()
                .enterpriseId(enterprise.getEnterpriseId())
                .enterpriseName(enterprise.getName())
                .city(enterprise.getCity())
                .socialPurpose(enterprise.getSocialPurpose())
                .district(enterprise.getDistrict())
                .website(enterprise.getWebsite())
                .latitude(enterprise.getLatitude())
                .longitude(enterprise.getLongitude())
                .build();
    }
}
