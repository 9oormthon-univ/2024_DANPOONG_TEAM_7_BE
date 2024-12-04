package danpoong.soenter.domain.job.entity;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    // 기본 정보
    private String title;
    private String field;
    private String duty;
    private String region;
    private String image;

    // 근무조건
    private String salary;
    private String workPeriod;
    private String workDays;
    private String workHours;
    private String jobType;
    private String employmentType;
    private String benefits;

    // 모집조건
    private String deadline;
    private String requiredPeriod;
    private String education;
    private String preference;

    // 근무지역
    private String detailAddress;

    // 채용담당자 정보
    private String manager;
    private String phone;
    private String email;
    private String website;
}