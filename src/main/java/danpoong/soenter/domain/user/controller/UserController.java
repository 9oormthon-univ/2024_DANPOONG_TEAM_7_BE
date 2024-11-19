package danpoong.soenter.domain.user.controller;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.user.dto.UserDTO.UserResponse.GetUserDetailResponse;
import danpoong.soenter.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "유저 관련 API")
public class UserController {

    private final UserService userService;
    @GetMapping
    @Operation(summary = "회원 프로필 조회 API", description = "사용자의 프로필 정보를 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<GetUserDetailResponse> getUserDetail(Authentication authentication) {
        GetUserDetailResponse userDetail = userService.getUserDetail(authentication.getName());
        return ApiResponse.onSuccess(userDetail);
    }
}
