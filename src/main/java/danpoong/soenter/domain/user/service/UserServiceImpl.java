package danpoong.soenter.domain.user.service;

import danpoong.soenter.domain.user.converter.UserConverter;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetUserDetailResponse;
import danpoong.soenter.domain.user.entity.User;
import danpoong.soenter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public GetUserDetailResponse getUserDetail(String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        return UserConverter.toGetUserDetailResponse(user);
    }
}
