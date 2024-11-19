package danpoong.soenter.domain.user.service;

import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.UpdateBirthResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserRequest.UpdateBirthRequest;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetUserDetailResponse;

public interface UserService {
    GetUserDetailResponse getUserDetail(String userId);
    UpdateBirthResponse updateBirth(String userId, UpdateBirthRequest request);
}
