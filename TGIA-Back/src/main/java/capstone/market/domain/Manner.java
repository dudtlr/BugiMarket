package capstone.market.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Manner {
    @Id @GeneratedValue
    private Long mannerId;
    @OneToOne(mappedBy = "manner", fetch = FetchType.LAZY)
    private Member member;
    private Long goodPrice = 0L;
    private Long goodTime = 0L;
    private Long fastResponse = 0L;
    private Long badQuality = 0L;
    private Long noResponse = 0L;
    private Long reDealing = 0L;
    private Long totalFeed = 0L;
}
