package danpoong.soenter.base.kakao.controller;

import danpoong.soenter.base.kakao.response.LoginSuccessResponse;
import danpoong.soenter.base.kakao.service.KakaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

}