package danpoong.soenter.base.jwt;

import danpoong.soenter.domain.user.entity.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

//사용자 인증 객체를 나타내고, 인증된 사용자에 대한 정보 포함. 주로 사용자 인증 및 권한 부여에 사용
public class UserAuthentication extends UsernamePasswordAuthenticationToken {
    private final String kakaoAccessToken;
    private final UserRole role;

    public UserAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String kakaoAccessToken, UserRole role) {
        super(principal, credentials, authorities);
        this.kakaoAccessToken = kakaoAccessToken;
        this.role = role;
    }

    public String getKakaoAccessToken() {
        return kakaoAccessToken;
    }

    public UserRole getRole() {
        return role;
    }
}