package danpoong.soenter.domain.user.dto;

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
    }

    public static class UserResponse {
        @Getter
        @Builder
        public static class GetUserDetailResponse {
            private String name;
            private String email;
            private LocalDate birth;
        }

        @Getter
        @Builder
        public static class UpdateBirthResponse {
            private Long userId;
            private String name;
            private String email;
            private String socialType;
            private LocalDate birth;
        }
    }
}
