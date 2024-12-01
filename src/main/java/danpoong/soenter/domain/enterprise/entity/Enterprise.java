package danpoong.soenter.domain.enterprise.entity;

import danpoong.soenter.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "enterprise")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enterpriseId;

    @Enumerated(EnumType.STRING)
    @Column
    private Region region;

    @Column
    private Boolean is_cooperative;

    @Column
    private String certificationNumber;

    @Column(nullable = false)
    private String name;

    @Column
    private String businessRegistrationNumber;

    @Column
    private String socialPurpose;

    @Column
    private String type;

    @Column
    private String representativeName;

    @Column
    private String representativePhone;

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private String website;

    @Column
    private Integer certificationYear;

    @OneToMany(mappedBy = "enterprise")
    private List<User> users;
}

