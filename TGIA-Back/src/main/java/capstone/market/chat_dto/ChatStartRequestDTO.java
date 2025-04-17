package capstone.market.chat_dto;

import lombok.Data;

@Data
public class ChatStartRequestDTO {
    Long post_id;
//    Long my_member_id;
    Long member_id;

    public ChatStartRequestDTO() {
    }

    public ChatStartRequestDTO(Long post_id, Long member_id) {
        this.post_id = post_id;
        this.member_id = member_id;
//        this.my_member_id = my_member_id;
    }
}
