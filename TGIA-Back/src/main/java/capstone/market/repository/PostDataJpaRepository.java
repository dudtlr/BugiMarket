package capstone.market.repository;


import capstone.market.domain.ChatRoom;
import capstone.market.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface PostDataJpaRepository extends JpaRepository<Post, Long> , PostRepositoryCustom {
    List<ChatRoom> findChatRoomsByPostId(Long postId);

    List<Post> findAllByOrderByCreatedDateDesc(Pageable pageable);



}
