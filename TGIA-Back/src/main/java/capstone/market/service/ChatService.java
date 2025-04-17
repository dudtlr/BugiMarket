package capstone.market.service;

import capstone.market.domain.*;
import capstone.market.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final PostDataJpaRepository postDataJpaRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberDataJpa memberDataJpa;

    public ChatMessage findChatMessageById(Long id) {
        return chatMessageRepository.findById(id).get();
    }

    public Post findPostByPostId(Long id) {
        return postDataJpaRepository.findById(id).get();
    }

    public Member findMemberByMemberId(Long id) {
        return memberDataJpa.findById(id).get();
    }

    public ChatRoom findChatRoomByChatRoomId(Long id) {
        return chatRoomRepository.findById(id).get();
    }

    public void updateLooked(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }
    public ChatRoom startChatRoomService(Post post, Member sender, Member receiver) {
        List<ChatRoom> chatRooms = post.getChatRooms();
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getMemberA() == receiver) {
                return chatRoom;
            }
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setPost(post);
        chatRoom.setMemberA(sender);
        chatRoom.setMemberB(receiver);
        chatRoomRepository.save(chatRoom);
        // 기존 채팅이 있는지 없는 지

        return chatRoom;
    }

    public ChatRoom startChatRoomServiceV2(Post post, Member sender, Member receiver) {
        // 현재 receiver 는 게시물의 작성자로 만약 sender, receiver 가 이전 채팅방 목록에 같은 짝이 있다면
        // 무조건 채팅방을 만들지 말라고 한다면, 게시글 작성자가 다른 글을 올렸을 때 문의하기를 할 수 없다.
        // 이를 방지하기 위해서 게시글 id가 같을 경우에만을 검사한다.

        List<ChatRoom> chatRooms = chatRoomRepository.findByPostPostId(post.getPostId());
        for (ChatRoom chatRoom: chatRooms) {
            if (chatRoom.getMemberA() == sender && chatRoom.getMemberB() == receiver) {
                return chatRoom;
            }
        }

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setPost(post);
        chatRoom.setMemberA(sender);
        chatRoom.setMemberB(receiver);
        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    public ChatMessage startChatMessageServiceV2(ChatRoom chatRoom, Member member, String message) {
        ChatMessage chatMessage = new ChatMessage(chatRoom, member, message, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        chatMessage.setMember(member);
        chatMessage.setChatRoom(chatRoom);
        chatMessage.setLooked(false);
        chatMessageRepository.save(chatMessage);

        chatRoomRepository.save(chatRoom);
        return chatMessage;
    }

    public ChatMessage startChatMessageService(ChatRoom chatRoom, Member member, String message) {
        ChatMessage chatMessage = new ChatMessage(chatRoom, member, message, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        chatMessage.setMember(member);
        chatMessage.setChatRoom(chatRoom);
        chatMessage.setLooked(false);
        chatMessageRepository.save(chatMessage);

        Long member_id = member.getId();

        System.out.println("$$$$$$$$ memberId = " +  member_id);
        System.out.println("$$$$$$$$ chatRoom.getMemberA().getId() = " + chatRoom.getMemberA().getId());
//        if(member.getId() == chatRoom.getMemberA().getId()) {
//            chatRoom.updateMessageCountB();
//            System.out.println("worked updateMessageCountB" + chatRoom.getCount_b());
//        } else {
//            chatRoom.updateMessageCountA();
//            System.out.println("worked updateMessageCountA " + chatRoom.getCount_a());
//        }
        chatRoomRepository.save(chatRoom);
        return chatMessage;
    }

    public List<ChatRoom> getChatRoomLists(Long id) {
        return chatRoomRepository.findByPostPostId(id);
    }

    public List<ChatRoom> getChatRoomListsByMemberId(Long member_id) {
        List<ChatRoom> chatRoomList = chatRoomRepository.findByMemberAId(member_id);
        List<ChatRoom> chatRooms = chatRoomRepository.findByMemberBId(member_id);
        chatRoomList.addAll(chatRooms);
        return chatRoomList;
    }

    public Long getUnreadMessageCount(Long chatroom_id) {
        return chatMessageRepository.countByLookedAndChatRoomId(false, chatroom_id);
    }

    public List<ChatMessage> getChatListsV2(Long id, Long member_id) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(id);
        ChatRoom chatRoom = chatRoomRepository.findById(id).get();
//        for (ChatMessage chatMessage : chatMessages) {
//            if (chatMessage.getMember().getId() == member_id) {
//                chatMessage.setLooked(true);
//            }
//        }
        return chatMessages;
    }

    public List<ChatMessage> getChatLists(Long id, Long member_id) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(id);
        ChatRoom chatRoom = chatRoomRepository.findById(id).get();
        Long memberB_id = chatRoom.getMemberB().getId();
        for (ChatMessage chatMessage : chatMessages) {
            if (chatMessage.getMember().getId() != memberB_id) {

            } else {
                chatMessage.setLooked(true);
//                chatRoom.setCount_a(0L);
//                chatRoom.setCount_b(0L);
            }
        }
        return chatMessages;
    }


}
