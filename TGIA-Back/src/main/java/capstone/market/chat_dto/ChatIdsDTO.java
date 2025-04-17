package capstone.market.chat_dto;

import capstone.market.domain.ChatRoom;
import lombok.Data;

@Data
public class ChatIdsDTO {

    private Long memberA_id;
    private Long memberB_id;

    public ChatIdsDTO(ChatRoom chatRoom) {
        this.memberA_id = chatRoom.getMemberA().getId();
        this.memberB_id = chatRoom.getMemberB().getId();
    }
}
