package danpoong.soenter.base.kakao.controller;

import danpoong.soenter.base.kakao.response.LoginSuccessResponse;
import danpoong.soenter.base.kakao.service.KakaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Kakao Login 컨트롤러", description = "카카오 로그인 관련 API")
public class KakaoController {
    private final KakaoService kakaoService;

    //TODO : 테스트용 나중에 지워야 함
    @Value("${kakao.get_code_path}")
    private String getCodePath;
    @Value("${kakao.client_id}")
    private String client_id;
    @Value(("${kakao.redirect_uri}"))
    private String redirect_uri;
    @Value(("${kakao.logout_redirect_uri}"))
    private String logout_redirect_uri;

    @Operation(summary = "카카오 로그인 페이지 URL 반환", description = "카카오 로그인 페이지로 리다이렉션하기 위한 URL을 반환합니다.")
    @GetMapping("/kakao/login")
    public ResponseEntity<String> loginPage() {
        String location = getCodePath + client_id + "&redirect_uri=" + redirect_uri;
        return ResponseEntity.ok(location);
    }

    @Operation(summary = "카카오 Access Token 요청", description = "카카오 인증 코드를 전달받아 Access Token과 사용자 정보를 반환합니다.")
    @PostMapping("/kakao/token")
    public ResponseEntity<LoginSuccessResponse> getAccessToken(@RequestParam("code") String code) {
        try {
            // Step 1: 인증 코드를 기반으로 Access Token 생성
            LoginSuccessResponse userResponse = kakaoService.kakaoLogin(code);

            // Step 2: Access Token 및 사용자 정보 반환
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            log.error("Access Token 생성 실패: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @Operation(summary = "카카오 로그아웃", description = "Authorization 헤더의 Access Token을 기반으로 카카오 로그아웃을 수행합니다.")
    @PostMapping("/kakao/logout")
    public ResponseEntity<String> logout(Authentication authentication) {
        try {
            return ResponseEntity.ok(kakaoService.kakaoLogout(authentication.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("로그아웃 처리 실패");
        }
    }

    @Operation(summary = "카카오 계정과 함께 로그아웃", description = "카카오 계정과 서비스 로그아웃을 함께 수행합니다.")
    @GetMapping("/kakao/account-logout")
    public ResponseEntity<String> logoutWithKakaoAccount() {
        try {
            // 카카오 계정과 함께 로그아웃 URL 생성
            String logoutUrl = "https://kauth.kakao.com/oauth/logout"
                    + "?client_id=" + client_id
                    + "&logout_redirect_uri=" + logout_redirect_uri;

            return ResponseEntity.ok(logoutUrl);
        } catch (Exception e) {
            log.error("카카오 계정과 함께 로그아웃 실패: {}", e.getMessage());
            return ResponseEntity.status(500).body("카카오 계정과 함께 로그아웃 처리 실패");
        }
    }

    @Operation(summary = "카카오 인증 콜백", description = "카카오 인증 후 백엔드에서 모든 처리를 수행하고 프론트엔드로 리다이렉트합니다.")
    @GetMapping("/kakao/callback")
    public ResponseEntity<Void> kakaoCallback(@RequestParam("code") String code, HttpServletResponse response) {
        try {
            // Step 1: 인증 코드를 기반으로 Access Token 생성
            LoginSuccessResponse userResponse = kakaoService.kakaoLogin(code);

            // Step 2: 프론트엔드로 리다이렉트 URL 생성
            String redirectUrl = "http://localhost:5173/loginSuccess"
                    + "?userId=" + userResponse.getUserId()
                    + "&accessToken=" + userResponse.getAccessToken()
                    + "&kakaoAccessToken=" + userResponse.getKakaoAccessToken();

            // Step 3: 프론트엔드로 리다이렉트
            response.sendRedirect(redirectUrl);
            return null;
        } catch (Exception e) {
            log.error("Access Token 생성 실패: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}