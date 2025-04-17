package capstone.market.chat_dto;

import capstone.market.domain.ChatMessage;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageResponseDTO {
    Long sender;
    Long message_id;
    String message;
    String time;
    Long chatroom_id;
    Boolean looked;

    public ChatMessageResponseDTO(ChatMessage chatMessage) {
        this.sender = chatMessage.getMember().getId();
        this.message_id = chatMessage.getId();
        this.message = chatMessage.getMessage();
        this.chatroom_id = chatMessage.getChatRoom().getId();
        this.time = createHourMinuteString(chatMessage);
        this.looked = chatMessage.getLooked();
    }

    private String createHourMinuteString(ChatMessage chatMessage) {
        String myTime;
        LocalDateTime dateTime = chatMessage.getTime();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
//        if (minute < 10) {
//              minute -= 10;
//        }
        String minuteString = String.valueOf((minute < 10) ? "0" + minute : minute);
        // 변수 = (조건) ? 참인 경우의 값 : 거짓인 경우의 값;
        if (hour > 12) {
            myTime = "오후 " + (hour - 12) + ":" + minuteString;
        } else {
            myTime = "오전 " + hour + minuteString;
        }
        return myTime;
    }
}
