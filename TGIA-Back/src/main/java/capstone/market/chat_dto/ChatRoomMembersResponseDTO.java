package capstone.market.chat_dto;

import lombok.Data;

@Data
public class ChatRoomMembersResponseDTO {
    private Long aId;
    private Long bId;

    public ChatRoomMembersResponseDTO() {
    }

    public ChatRoomMembersResponseDTO(Long aId, Long bId) {
        this.aId = aId;
        this.bId = bId;
    }
}
