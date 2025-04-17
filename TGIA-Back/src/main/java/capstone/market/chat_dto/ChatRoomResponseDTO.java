package capstone.market.chat_dto;

import capstone.market.domain.ChatRoom;
import lombok.Data;

@Data
public class ChatRoomResponseDTO {
    Long chatroom_id;
    Long count;
//    String final_message;
    Long member;
//    Long member_a;

    public ChatRoomResponseDTO(ChatRoom chatRoom) {
        this.chatroom_id =chatRoom.getId();
        this.member = chatRoom.getMemberA().getId();
//        this.member_a = chatRoom.getMember().getId();
        this.count = 0L;
    }

    //    String member_a;
//    String member_b;
//
//    public ChatRoomResponseDTO(ChatRoom chatRoom) {
//        this.member_a = chatRoom.getPost().getWho_posted().getUsername();
//        this.member_b = chatRoom.getMember().getUsername();
//    }
}
