package danpoong.soenter.domain.user.entity;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    @Column
    private LocalDate birth;

    @Column
    private String city;

    @Column
    private String socialType;

    @Column
    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인 : null)

    @Column
    private String kakaoAccess;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.USER;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    public void updateBirthDate(LocalDate birth) {
        this.birth = birth;
    }

    public void updateKakaoAccess(String kakaoAccess) {
        this.kakaoAccess = kakaoAccess;
    }

    public void updateCityDate(String city) {
        this.city = city;
    }

    public void updateEnterprise(Long enterpriseId) {
        this.enterprise = Enterprise.builder().enterpriseId(enterpriseId).build();
    }

    public void upgradeToEnterprise() {
        this.role = UserRole.ENTERPRISE;
    }
}
