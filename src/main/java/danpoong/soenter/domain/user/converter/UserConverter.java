package danpoong.soenter.domain.user.converter;

import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetUserDetailResponse;
import danpoong.soenter.domain.user.entity.User;

public class UserConverter {
    public static GetUserDetailResponse toGetUserDetailResponse(User user) {
        return GetUserDetailResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
