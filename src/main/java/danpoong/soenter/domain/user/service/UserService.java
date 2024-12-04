package danpoong.soenter.domain.user.service;

import danpoong.soenter.domain.user.dto.UserDTO.UserRequest.UpdateCityRequest;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.UpdateBirthResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.UpdateCityResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserRequest.UpdateBirthRequest;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetUserDetailResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetEnterpriseUserResponse;

public interface UserService {
    GetUserDetailResponse getUserDetail(String userId);
    UpdateBirthResponse updateBirth(String userId, UpdateBirthRequest request);
    UpdateCityResponse updateCity(String userId, UpdateCityRequest request);
    GetEnterpriseUserResponse getEnterpriseUserDetail(String userId);
}
