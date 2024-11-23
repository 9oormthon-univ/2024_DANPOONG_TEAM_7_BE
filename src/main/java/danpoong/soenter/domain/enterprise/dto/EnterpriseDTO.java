package danpoong.soenter.domain.enterprise.dto;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Region;
import lombok.Builder;
import lombok.Getter;

public class EnterpriseDTO {
    public static class EnterpriseResponse {
        @Getter
        @Builder
        public static class GetEnterpriseResponse {
            private Long enterpriseId;
            private String name;
            private Region region;
            private Boolean isCooperative;
            private String certificationNumber;
            private String type;
            private String city;
            private String district;
            private double latitude;
            private double longitude;
            private String website;
            private Integer certificationYear;
            private int reviewCount;
        }
    }
}
