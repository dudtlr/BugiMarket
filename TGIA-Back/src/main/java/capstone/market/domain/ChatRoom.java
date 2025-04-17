package capstone.market.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ChatRoom{
    @Id @GeneratedValue
    @Column(name = "chatroom_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_a")
    private Member memberA;

    @ManyToOne
    @JoinColumn(name = "member_b")
    private Member memberB;

//    @ManyToOne
//    @JoinColumn(name = "sender")
//    private Member sender;

//    @ManyToOne
//    @JoinColumn(name = "member")
//    private Member member;
//    private Long count_a = 0L;
//    private Long count_b = 0L;

    //latest_msg
    @OneToOne
    private ChatMessage finalMsg;
    private String finalMsgString;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<ChatMessage> messages = new ArrayList<>();

//    public void updateMessageCountA() {
//        this.count_a += 1;
//    }
//
//    public void updateMessageCountB() {
//        this.count_b += 1;
//    }
}
