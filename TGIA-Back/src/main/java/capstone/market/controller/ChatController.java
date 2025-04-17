package capstone.market.controller;

import capstone.market.chat_dto.*;
import capstone.market.domain.ChatMessage;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.service.ChatService;
import capstone.market.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final MemberService memberService;

    // 게시물 페이지에서 채팅방 리스트를 조회할 경우
    // 게시물의 id(PK)를 넘겨줘야 됩니다.
    @GetMapping("/chat/get_chat_room_list")
    public List<ChatRoomListResponseDTO> getChatRoomList(Long id, Long member_id) {
        List<ChatRoom> chatRoomLists = chatService.getChatRoomLists(id);
        List<ChatRoomListResponseDTO> chatRoomResponseDTOS = new ArrayList<>();
        Long count;
        for (ChatRoom chatRoom : chatRoomLists) {
            int size = chatRoom.getMessages().size();
            ChatMessage chatMessage;
            if (size != 0) {
                chatMessage = chatRoom.getMessages().get(size-1);
            } else {
                chatMessage = null;
            }
//            6/1일 이전 코드
//            if (chatMessage.getMember() != null && chatMessage.getMember().getId() == member_id) {
            if (chatMessage.getMember().getId() == member_id) {
                count = 0L;
            } else {
                count = chatService.getUnreadMessageCount(chatRoom.getId());
            }
            System.out.println("7878788778 count = " + count);
//            if (member_id == chatRoom.getMemberA().getId()) {
//                count = chatRoom.getCount_a();
//            } else {
//                count = chatRoom.getCount_b();
//            }
            ChatRoomListResponseDTO roomResponseDTO = new ChatRoomListResponseDTO(chatRoom, chatMessage, count);
            chatRoomResponseDTOS.add(roomResponseDTO);
        }
        return chatRoomResponseDTOS;
    }

    /*
    멤버 id를 주면 해당 멤버가 참가하고 있는 채팅방 리스트 띄우기
    기존 ChatRoomListResponseDTO에 post_id를 추가해서 넘겨주기
     */

    @GetMapping("/chat/get_last_message")
    public String getLastMessage(Long id) {
        ChatMessage message = chatService.findChatMessageById(id);
        return message.getMessage();
    }

    @GetMapping("/chat/get_username")
    public String getUsername(Long id) {
        Member member = memberService.findOne(id);
        return member.getUsername();
    }

    @GetMapping("/chat/get_ids")
    public ChatIdsDTO getChatmembersIds(@RequestParam Long chatroom_id) {
        return new ChatIdsDTO(chatService.findChatRoomByChatRoomId(chatroom_id));
    }

    // 채팅방에 저장된 채팅 메시지 내역을 가져옵니다.
    // 채팅방(ChatRoom) id(PK)를 넘겨줘야 됩니다.
    @GetMapping("/chat/get_chat_message_list")
    public List<ChatMessageResponseDTO> getChatMessageList(Long id, Long member_id) {
        List<ChatMessage> chatLists = chatService.getChatLists(id, member_id);
        List<ChatMessageResponseDTO> chatMessageResponseDTOS = new ArrayList<>();

        System.out.println("/chat/get_chat_message_list");

        for (ChatMessage chatMessage : chatLists) {
            if (chatMessage.getMember().getId() != member_id) {
                System.out.println("chatMessage.setLooked(true);");
                chatMessage.setLooked(true);
                chatService.updateLooked(chatMessage);
            }
            chatMessageResponseDTOS.add(new ChatMessageResponseDTO(chatMessage));
        }

        return chatMessageResponseDTOS;
    }

    // 문의하기 버튼을 눌렀을 경우 -> 채팅방 참가자 정보를 리턴합니다.
    /* 리턴 타입
        {
        "member_a": "더미부기",
        "member_b": "건희"
        }
     */

    /*
    chatroom_id, member_id를 주면 -> 그거에 해당하는 메시지 중에서 looked가 0인
     */

//    @PostMapping("/chat/startV2")
//    public ChatRoomResponseDTO chatStartV2(@RequestBody ChatStartRequestDTO chatStartRequestDTO) {
//        Long postId = chatStartRequestDTO.getPost_id();
//        Long myId = chatStartRequestDTO.getMy_member_id();
//        Long sender_id = chatStartRequestDTO.getMember_id();
//        Post post = chatService.findPostByPostId(postId);
//        Member sender = chatService.findMemberByMemberId(sender_id);
////        chatService.findMemberByMemberId(sender_id);
//        Member receiver = post.getWho_posted();
//
//        chatService.startChatRoomServiceV2(post, sender, receiver);
//        chatService.startChatRoomServiceV2(post, sender, receiver);
//
//        if (myId == )
//    }

    @PostMapping("/chat/start")
    public ChatRoomResponseDTO chatStart(@RequestBody ChatStartRequestDTO chatStartRequestDTO) {
        System.out.println("chatStartRequestDTO.getPost_id() = " + chatStartRequestDTO.getPost_id());

        Long post_id = Long.valueOf(chatStartRequestDTO.getPost_id());
        Long member_id = Long.valueOf(chatStartRequestDTO.getMember_id());

        Post post = chatService.findPostByPostId(post_id);
        Member sender = chatService.findMemberByMemberId(member_id);
        Member receiver = post.getWho_posted();

//        ChatRoom chatRoom = chatService.startChatRoomService(post, sender, receiver);
        ChatRoom chatRoom = chatService.startChatRoomServiceV2(post, sender, receiver);

        return new ChatRoomResponseDTO(chatRoom);
    }

    // chat room id 주면 참여자 a, b 전송
    @GetMapping("/chat/get_chat_members")
    public ChatRoomMembersResponseDTO getChatMembers(Long chatroom_id) {
        ChatRoom chatRoom = chatService.findChatRoomByChatRoomId(chatroom_id);
        Long aId = chatRoom.getMemberA().getId();
        Long bId = chatRoom.getMemberB().getId();
        return new ChatRoomMembersResponseDTO(aId, bId);
    }

    @GetMapping("/chat/get_chatroom_member_id_V2")
    public List<ChatRoomListResponseDTO> getChatListByMemberIdV2(Long member_id) {
        List<ChatRoom> chatRoomLists = chatService.getChatRoomListsByMemberId(member_id);
        System.out.println("dfafasdfsdf" + member_id);

        List<ChatRoomListResponseDTO> chatRoomResponseDTOS = new ArrayList<>();
        Long count;
        for (ChatRoom chatRoom : chatRoomLists) {
            int size = chatRoom.getMessages().size();
            ChatMessage chatMessage;
            if (size != 0) {
                chatMessage = chatRoom.getMessages().get(size-1);
                System.out.println("313123");
            } else {
                System.out.println("3412341234");
                chatMessage = null;
            }
            if (chatMessage.getMember().getId() == member_id) {
                System.out.println("1341234");
                count = 0L;
            } else {
                System.out.println("13413444");
                count = chatService.getUnreadMessageCount(chatRoom.getId());
            }
            System.out.println("7878788778 count = " + count);
//            if (member_id == chatRoom.getMemberA().getId()) {
//                count = chatRoom.getCount_a();
//            } else {
//                count = chatRoom.getCount_b();
//            }
            ChatRoomListResponseDTO roomResponseDTO = new ChatRoomListResponseDTO(chatRoom, chatMessage, count);
            chatRoomResponseDTOS.add(roomResponseDTO);
        }
        return chatRoomResponseDTOS;
    }
    @GetMapping("/chat/get_chatroom_member_id")
    public List<ChatRoomListResponseDTO> getChatListByMemberId(Long member_id) {
        List<ChatRoom> chatRoomLists = chatService.getChatRoomListsByMemberId(member_id);
        System.out.println("dfafasdfsdf" + member_id);

        List<ChatRoomListResponseDTO> chatRoomResponseDTOS = new ArrayList<>();
        Long count;
        for (ChatRoom chatRoom : chatRoomLists) {
            int size = chatRoom.getMessages().size();
            ChatMessage chatMessage;
            if (size != 0) {
                chatMessage = chatRoom.getMessages().get(size-1);
                System.out.println("313123");
            } else {
                System.out.println("3412341234");
                chatMessage = null;
            }
            // 6/1
//            if (chatMessage.getMember() != null && Objects.equals(chatMessage.getMember().getId(), member_id)) { // null pointer error
            if (Objects.equals(chatMessage.getMember().getId(), member_id)) { // null pointer error
                System.out.println("1341234");
                count = 0L;
            } else {
                System.out.println("13413444");
                count = chatService.getUnreadMessageCount(chatRoom.getId());
            }
            System.out.println("7878788778 count = " + count);
//            if (member_id == chatRoom.getMemberA().getId()) {
//                count = chatRoom.getCount_a();
//            } else {
//                count = chatRoom.getCount_b();
//            }
            ChatRoomListResponseDTO roomResponseDTO = new ChatRoomListResponseDTO(chatRoom, chatMessage, count);
            chatRoomResponseDTOS.add(roomResponseDTO);
        }
        return chatRoomResponseDTOS;
    }

//    @PostMapping("/chat/start_ver2")
//    public ChatRoomResponseDTO chatStartVer2(@RequestBody ChatStartRequestDTO chatStartRequestDTO) {
//        System.out.println("chatStartRequestDTO.getPost_id() = " + chatStartRequestDTO.getPost_id());
//
//        Long post_id = Long.valueOf(chatStartRequestDTO.getPost_id());
//        Long member_id = Long.valueOf(chatStartRequestDTO.getMember_id());
//
//        Post post = chatService.findPostByPostId(post_id);
//        Member receiver = post.getWho_posted();
//        Member member = chatService.findMemberByMemberId(member_id);
//
//        ChatRoom chatRoom = chatService.startChatRoomService(post, member);
//
//        return new ChatRoomResponseDTO(chatRoom);
//    }

    // chat_room_id, member_id, message 필요
    // 메시지 전송 버튼을 눌렀을 경우
    @PostMapping("/chat/send")
    public ChatMessageResponseDTO sendMessage(@RequestBody SendMessageRequestDTO sendMessageRequestDTO) {

        Long chatroom_id = Long.valueOf(sendMessageRequestDTO.getChatroom_id());
        Long sender_id = Long.valueOf(sendMessageRequestDTO.getSender_id());
        String message = sendMessageRequestDTO.getMessage();

        ChatRoom chatRoom = chatService.findChatRoomByChatRoomId(chatroom_id);
        Member member = chatService.findMemberByMemberId(sender_id);

        ChatMessage chatMessage = chatService.startChatMessageService(chatRoom, member, message);
        // 방금 전송한 메시지를 반환합니다 -> 본인이 전송한 것을 화면에 뿌려주기 위해서 그런데 프론트 안에서도 해결 가능?

        return new ChatMessageResponseDTO(chatMessage);
    }

    @PostMapping("/chat/send_V2")
    public List<ChatMessageResponseDTO> sendMessageV2(@RequestBody SendMessageRequestDTO sendMessageRequestDTO) {

        Long chatroom_id = sendMessageRequestDTO.getChatroom_id();
        Long sender_id = sendMessageRequestDTO.getSender_id();
        String message = sendMessageRequestDTO.getMessage();

        ChatRoom chatRoom = chatService.findChatRoomByChatRoomId(chatroom_id);
        Member member = chatService.findMemberByMemberId(sender_id);

        ChatMessage chatMessage = chatService.startChatMessageService(chatRoom, member, message);
        // 방금 전송한 메시지를 반환합니다 -> 본인이 전송한 것을 화면에 뿌려주기 위해서 그런데 프론트 안에서도 해결 가능?

        chatRoom.setFinalMsg(chatMessage);
        chatRoom.setFinalMsgString(chatMessage.getMessage());
        List<ChatMessage> chatLists = chatService.getChatListsV2(chatroom_id, sender_id);
        List<ChatMessageResponseDTO> chatMessageResponseDTOS = new ArrayList<>();

        for (ChatMessage chatMessage2 : chatLists) {
            chatMessageResponseDTOS.add(new ChatMessageResponseDTO(chatMessage2));
        }

        return chatMessageResponseDTOS;
//        return new ChatMessageResponseDTO(chatMessage);
    }
}
