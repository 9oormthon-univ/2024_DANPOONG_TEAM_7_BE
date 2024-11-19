package danpoong.soenter.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

public class UserDTO {
    public static class UserResponse {
        @Getter
        @Builder
        public static class GetUserDetailResponse {
            private String name;
            private String email;
        }
    }
}
