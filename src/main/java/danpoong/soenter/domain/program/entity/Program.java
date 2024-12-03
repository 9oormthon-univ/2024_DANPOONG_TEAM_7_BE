package danpoong.soenter.domain.program.entity;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;

    @ManyToOne
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column
    private String title;

    @Column
    private String field;

    @Column
    private String time;

    @Column
    private String region;

    @Column
    private String image;

    @Column
    private String content;
}
