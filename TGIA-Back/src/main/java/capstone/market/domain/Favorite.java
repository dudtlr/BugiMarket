package capstone.market.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Favorite extends BaseEntity {

    @Id @GeneratedValue
    private Long fav_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Favorite(Member member, Post post) {
        this.member = member;
        this.post = post;
    }

}
