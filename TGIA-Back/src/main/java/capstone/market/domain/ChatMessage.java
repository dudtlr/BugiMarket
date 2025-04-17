package capstone.market.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter
public class ChatMessage {
    @Id @GeneratedValue
    @Column(name = "msg_id")
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member member;
    @Column(name = "looked")
    private Boolean looked;

    LocalDateTime time;

    public void setLookedTrue() {
        this.looked = true;
    }

    public ChatMessage(String message) {
        this.message = message;
    }

    public ChatMessage(ChatRoom chatRoom, Member member, String message, LocalDateTime time) {
        this.message = message;
        this.chatRoom = chatRoom;
        this.member = member;
        this.time = time;
    }
}
