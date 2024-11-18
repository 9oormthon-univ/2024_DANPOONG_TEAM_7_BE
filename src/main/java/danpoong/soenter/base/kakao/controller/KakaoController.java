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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
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
    public String loginPage(Model model) {
        String location = getCodePath + client_id + "&redirect_uri=" + redirect_uri;
        model.addAttribute("location", location);
        return "login";
    }

    @Operation(summary = "카카오 로그인 콜백", description = "카카오 로그인 인증 코드로 사용자 정보를 조회합니다.")
    @GetMapping("/kakao/callback")
    public ResponseEntity<LoginSuccessResponse> callback(@RequestParam("code") String code) {
        try {
            LoginSuccessResponse userResponse = kakaoService.kakaoLogin(code);
            return ResponseEntity.ok().body(userResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}