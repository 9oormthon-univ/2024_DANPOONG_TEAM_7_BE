package danpoong.soenter.domain.likes.dto;

import lombok.Builder;
import lombok.Getter;

public class LikesDTO {

    public static class LikesResponse {
        @Getter
        @Builder
        public static class GetLikeResponse {
            private Long enterpriseId;
            private String enterpriseName;
            private String city;
            private String socialPurpose;
            private String district;
            private String website;
            private Double latitude;
            private Double longitude;
        }
    }
}
