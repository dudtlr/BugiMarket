package capstone.market.repository;

import capstone.market.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByPostPostId(Long postId);
    List<ChatRoom> findByMemberAId(Long memberId);
    List<ChatRoom> findByMemberBId(Long memberId);
//    List<ChatRoom> finByPostWhoPosted(Long memberId);


}
