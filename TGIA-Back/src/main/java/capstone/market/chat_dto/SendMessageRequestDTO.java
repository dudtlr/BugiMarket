package capstone.market.chat_dto;

import lombok.Data;

@Data
public class SendMessageRequestDTO {
    private Long chatroom_id;
    private Long sender_id;
    private String message;

    public SendMessageRequestDTO() {

    }

    public SendMessageRequestDTO(Long chatroom_id, Long sender_id, String message) {
        this.chatroom_id = chatroom_id;
        this.sender_id = sender_id;
        this.message = message;
    }
}
