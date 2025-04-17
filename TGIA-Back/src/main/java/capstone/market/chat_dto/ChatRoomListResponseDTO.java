package capstone.market.chat_dto;

import capstone.market.domain.ChatMessage;
import capstone.market.domain.ChatRoom;
import lombok.Data;

@Data
public class ChatRoomListResponseDTO {
    Long post_id;
    Long chatroom_id;
    Long count;
//    String final_message;
    Long member_a;
    Long member_b;
    Long last_chatMessage;
    String last_chatMessage_String;

    public ChatRoomListResponseDTO(ChatRoom chatRoom, ChatMessage message, Long count) {
        this.post_id = chatRoom.getPost().getPostId();
        this.chatroom_id = chatRoom.getId();
        this.last_chatMessage = message.getId();
        this.count = count;
//        this.final_message = message;
        this.member_a = chatRoom.getMemberA().getId();
        this.member_b = chatRoom.getMemberB().getId();
        this.last_chatMessage_String = chatRoom.getFinalMsgString();
//        this.member_b = chatRoom.getPost().getWho_posted().getId();
    }
}
