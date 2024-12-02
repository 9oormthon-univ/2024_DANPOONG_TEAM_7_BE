package danpoong.soenter.base.kakao.service;

import danpoong.soenter.base.jwt.JwtTokenProvider;
import danpoong.soenter.base.jwt.UserAuthentication;
import danpoong.soenter.base.kakao.response.KakaoTokenResponseDto;
import danpoong.soenter.base.kakao.response.KakaoUserInfoResponseDto;
import danpoong.soenter.base.kakao.response.LoginSuccessResponse;
import danpoong.soenter.domain.user.entity.User;
import danpoong.soenter.domain.user.entity.UserRole;
import danpoong.soenter.domain.user.repository.UserRepository;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;
    private final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    @Transactional
    public LoginSuccessResponse kakaoLogin(String code) {
        String accessToken = getAccessToken(code);
        KakaoUserInfoResponseDto userInfo = getUserInfo(accessToken);
        Long userId = getUserByEmail(userInfo.getKakaoAccount().email).getUserId();
        return getTokenByUserId(userId, accessToken);
    }

    public String getAccessToken(String code) {
        KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", client_id)
                        .queryParam("redirect_uri", redirect_uri)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    //log.error("액세스 토큰 발급 시 4xx 에러 발생: {}", clientResponse.statusCode());
                    return clientResponse.createException().flatMap(Mono::error);
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    //log.error("액세스 토큰 발급 시 5xx 에러 발생: {}", clientResponse.statusCode());
                    return clientResponse.createException().flatMap(Mono::error);
                })
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        assert kakaoTokenResponseDto != null;
        return kakaoTokenResponseDto.getAccessToken();
    }



    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {
        KakaoUserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST).get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        if (!isDuplicateEmail(userInfo.getKakaoAccount().email)) {
            User newUser = User.builder()
                    .email(userInfo.getKakaoAccount().email)
                    .name(userInfo.getKakaoAccount().getProfile().nickName)
                    .socialType("kakao")
                    .role(UserRole.USER)
                    .build();
            userRepository.save(newUser);
        }

        User user = userRepository.findByEmail(userInfo.getKakaoAccount().email).orElse(null);
        if (user.getKakaoAccess() == null || user.getKakaoAccess().isEmpty()) {
            System.out.println("ok");
            // kakaoAccessToken이 비어 있다면 업데이트
            user.updateKakaoAccess(accessToken);
            userRepository.save(user);
        }

        return userInfo;
    }


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("이메일이 없습니다."));
    }

    public LoginSuccessResponse getTokenByUserId(Long userId, String kakaoAccessToken) {
        // 사용자 role 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not Found User"));

        // 권한 정보 생성
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );

        UserAuthentication userAuthentication = new UserAuthentication(
                userId,
                null,
                authorities,
                kakaoAccessToken,
                user.getRole()
        );

        String accessToken = jwtTokenProvider.generateToken(userAuthentication);

        return new LoginSuccessResponse(userId, accessToken, kakaoAccessToken);
    }

    private boolean isDuplicateEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public String kakaoLogout(String userId) {
        try {
            User user = userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

            String accessToken = user.getKakaoAccess();

            String result = WebClient.create(KAUTH_USER_URL_HOST).post()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .path("/oauth/logout")
                            .build(true))
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            user.updateKakaoAccess(null);
            userRepository.save(user);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Kakao logout request failed", e);
        }
    }

}