package danpoong.soenter.domain.review.entity;

import goorm.ssoenter.domain.enterprise.entity.Enterprise;
import goorm.ssoenter.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "enterpriseId", nullable = false)
    private Enterprise enterprise;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Integer tagNum;

    @Column
    private LocalDate createAt;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagList> tagList;
}
