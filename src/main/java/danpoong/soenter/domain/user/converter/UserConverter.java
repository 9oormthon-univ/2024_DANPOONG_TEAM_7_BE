package danpoong.soenter.domain.user.converter;

import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.UpdateCityResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.UpdateBirthResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetUserDetailResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetEnterpriseUserResponse;
import danpoong.soenter.domain.user.entity.User;

public class UserConverter {
    public static GetUserDetailResponse toGetUserDetailResponse(User user) {
        return GetUserDetailResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .userRole(user.getRole())
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

    public static GetEnterpriseUserResponse toGetEnterpriseUserResponse(User user, long reviewCount, long programCount, long jobCount) {
        return GetEnterpriseUserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .userRole(user.getRole())
                .enterpriseName(user.getEnterprise().getName())
                .reviewCount(reviewCount)
                .programCount(programCount)
                .jobCount(jobCount)
                .build();
    }
}
