package capstone.market.repository;

import capstone.market.domain.*;
import capstone.market.domain.ChatRoom;
import capstone.market.domain.Post;
import capstone.market.profile_dto.PostDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public void savePost(Post post) {
        em.persist(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p")
                .getResultList();
    }

    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@.setParameter("keyword", keyword)
    public List<Post> SearchByTitle(String keyword) {

       // String jpql = "SELECT p FROM Post p WHERE p.post_title =:keyword";
        String jpql = "SELECT p FROM Post p WHERE p.post_title LIKE CONCAT('%', :keyword, '%')";

        return em.createQuery(jpql, Post.class)
                .setParameter("keyword",  keyword )
                .setMaxResults(10)
                .getResultList();
    }

    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@

    //@@@@@@@@@@@@@@@@@카테고리로 포스트 필터링@@@@@@@@@@@@@@@@@@@
    public List<Post> SearchByCategory(CategoryType category) {

         String jpql = "SELECT p FROM Post p WHERE p.category.category_type =:category";


        return em.createQuery(jpql, Post.class)
                .setParameter("category",  category )
                .setMaxResults(10)
                .getResultList();
    }

    //@@@@@@@@@@@@@@@@@카테고리로 포스트 필터링@@@@@@@@@@@@@@@@@@@


    public List<Post> findByUserId(String user_id) {
        String jpql = "select p from Post p join p.who_posted m where m.user_id = :user_id";

        return em.createQuery(jpql, Post.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }

    public List<ChatRoom> findByPostId(Long room_id) {
        String jpql = "select p from Post p join ChatRoom c where c.id = :room_id";

        return em.createQuery(jpql, ChatRoom.class)
                .setParameter("room_id", room_id)
                .getResultList();
    }

    // 구매 목록
    public List<Post> findBoughtListByUserId(Long user_id) {

        String jpql2 = "select p from Post p where p.purchased.member.id = :user_id and p.purchased is not null ";


        List<Post> list = em.createQuery(jpql2, Post.class)
                .setParameter("user_id", user_id)
                .getResultList();

        return list;
    }

    public Long findSellListCount(Long userId){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.who_posted.id =: userId and p.purchased is NULL";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("userId", userId)
                .getSingleResult();
        return count;

    }

    public List<PostDetailDto> findSellList(Long userId) {
        String jpql = "SELECT p FROM Post p WHERE p.who_posted.id =:userId AND p.purchased IS NULL ORDER BY p.createdDate DESC";
        TypedQuery<Post> query = em.createQuery(jpql, Post.class);
        query.setParameter("userId", userId);
        query.setMaxResults(4); // 최대 4개의 결과만 반환하도록 설정
        List<Post> resultList = query.getResultList();
        List<PostDetailDto> SearchPosts = resultList.stream().map(p -> new PostDetailDto(p))
                .collect(Collectors.toList());
        return SearchPosts;
    }



    public List<PostDetailDto> findListByCategory(CategoryType categoryType) {
        String jpql = "SELECT p FROM Post p WHERE p.category.category_type =: categoryType AND p.purchased IS NULL ORDER BY p.createdDate DESC";
        TypedQuery<Post> query = em.createQuery(jpql, Post.class);
        query.setParameter("categoryType", categoryType);
        query.setMaxResults(8); // 최대 4개의 결과만 반환하도록 설정
        List<Post> resultList = query.getResultList();

        List<PostDetailDto> SearchPosts = resultList.stream().map(p -> new PostDetailDto(p))
                .collect(Collectors.toList());
        return SearchPosts;

    }

    private static final int BATCH_SIZE = 500;

    public List<Post> saveAllBatch(List<? extends Post> posts) {
        int count = 0;
        List<Post> savedPosts = new ArrayList<>();

        for (Post post : posts) {
            em.persist(post); // 엔티티를 영속성 컨텍스트에 저장
            savedPosts.add(post); // 저장된 엔티티를 반환할 리스트에 추가
            count++;

            if (count % BATCH_SIZE == 0) {
                em.flush(); // 현재 배치를 데이터베이스에 저장
                em.clear(); // 영속성 컨텍스트 초기화하여 메모리 사용량 감소
            }
        }

        em.flush(); // 남은 엔티티를 저장
        em.clear(); // 남은 엔티티를 비우기

        return savedPosts; // 저장된 모든 엔티티를 반환
    }





}
