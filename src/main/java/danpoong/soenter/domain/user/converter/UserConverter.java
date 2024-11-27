package danpoong.soenter.domain.user.converter;

import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.UpdateCityResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.UpdateBirthResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetUserDetailResponse;
import danpoong.soenter.domain.user.entity.User;

public class UserConverter {
    public static GetUserDetailResponse toGetUserDetailResponse(User user) {
        return GetUserDetailResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .build();
    }

    public static UpdateBirthResponse toUpdateBirthDateResponse(User user) {
        return UpdateBirthResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .socialType(user.getSocialType())
                .birth(user.getBirth())
                .city(user.getCity())
                .build();
    }

    public static UpdateCityResponse toUpdateCityResponse(User user) {
        return UpdateCityResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .socialType(user.getSocialType())
                .birth(user.getBirth())
                .city(user.getCity())
                .build();
    }
}
