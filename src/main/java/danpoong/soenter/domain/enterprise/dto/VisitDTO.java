package danpoong.soenter.domain.enterprise.dto;

import lombok.Builder;
import lombok.Getter;

public class VisitDTO {
    public static class VisitResponse {
        @Getter
        @Builder
        public static class GetVisitedEnterpriseResponse {
            private Long enterpriseId;
            private String enterpriseName;
            private String city;
            private String district;
            private String website;
        }
    }
}
