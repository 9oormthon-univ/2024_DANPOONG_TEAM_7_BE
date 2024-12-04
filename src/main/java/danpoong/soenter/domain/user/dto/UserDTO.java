package danpoong.soenter.domain.user.dto;

import danpoong.soenter.domain.user.entity.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class UserDTO {
    public static class UserRequest {
        @Getter
        @NoArgsConstructor
        public static class UpdateBirthRequest {
            private LocalDate birth;
        }

        @Getter
        @NoArgsConstructor
        public static class UpdateCityRequest {
            private String city;
        }
    }

    public static class UserResponse {
        @Getter
        @Builder
        public static class GetUserDetailResponse {
            private String name;
            private String email;
            private LocalDate birth;
            private UserRole userRole;
        }

        @Getter
        @Builder
        public static class UpdateBirthResponse {
            private Long userId;
            private String name;
            private String email;
            private String socialType;
            private LocalDate birth;
            private String city;
        }

        @Getter
        @Builder
        public static class UpdateCityResponse {
            private Long userId;
            private String name;
            private String email;
            private String socialType;
            private LocalDate birth;
            private String city;
        }

        @Getter
        @Builder
        public static class GetEnterpriseUserResponse {
            private Long userId;
            private String name;
            private String email;
            private LocalDate birth;
            private UserRole userRole;

            private String enterpriseName;
            private long reviewCount;

            private long programCount;
            private long jobCount;
        }
    }
}
