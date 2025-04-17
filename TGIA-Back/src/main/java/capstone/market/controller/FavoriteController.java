package capstone.market.controller;

import capstone.market.domain.Favorite;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.post_dto.PostListResponse;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.service.FavoriteService;
import capstone.market.service.MemberService;
import capstone.market.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FavoriteController {


    private final FavoriteService favoriteService;
    private final MemberService memberService;
    private final PostService postService;

    //좋아요가 있는지 없는 지  있으면 1 없으면 0

    @GetMapping("/profile/is_favorite")   //좀 애매 하네 어디서든 지 포스트 검색 할 수 있음
    public Integer IsFavorite(@RequestParam("postId") Long PostId, @RequestParam("userId") Long userId) {
        Member user = memberService.findOne(userId);
        Post post = postService.findPostByPostId(PostId);


            List<Favorite> favorites = user.getFavorites(); // 유저의 좋아요 리스트 가져온다.


            for (Favorite favorite : favorites) {
                Long postId = favorite.getPost().getPostId();
                if (postId.equals(PostId)) {
                    return 1;
                }
            }

        return 0;

    }

    //좋아요 추가
    @PostMapping("/profile/add_favorite")   //좀 애매 하네 어디서든 지 포스트 검색 할 수 있음
    public Long addFavorite(@RequestParam Long postId, @RequestParam Long userId) {

          Member user = memberService.findOne(userId);
          Post post = postService.findPostByPostId(postId);
          post.setLikes(post.getLikes()+ 1);
          Favorite favorite = new Favorite(user, post);
          user.getFavorites().add(favorite);
          postService.savePost(post);
          favoriteService.save(favorite);
          return favorite.getFav_id();
    }

    //좋아요 삭제
    @DeleteMapping("/profile/delete_favorite")   //좀 애매 하네 어디서든 지 포스트 검색 할 수 있음
    public void deleteFavorite(@RequestParam Long favoriteId) {
        Favorite findFavorite = favoriteService.findById(favoriteId);
        findFavorite.getPost().setLikes(findFavorite.getPost().getLikes() - 1);
        postService.savePost(findFavorite.getPost());
        favoriteService.delete(favoriteId);
    }

    @DeleteMapping("/profile/delete_favorite3")   //좀 애매 하네 어디서든 지 포스트 검색 할 수 있음
    public void deleteFavorite3(@RequestParam Long postId,@RequestParam Long userId) {
        Member findmember = memberService.findOne(userId);
        List<Favorite> favorites = findmember.getFavorites();
        for(Favorite favorite : favorites){
            if(Objects.equals(favorite.getPost().getPostId(), postId)){
                Favorite findFavorite = favorite;
                findFavorite.getPost().setLikes(findFavorite.getPost().getLikes()-1);
                postService.savePost(findFavorite.getPost());
                favoriteService.delete(findFavorite.getFav_id());
            }
        }
    }

    //내 좋아요 리스트 => 좋아요가 선택된 리스트
    @GetMapping("/profile/favorite_list")
    public List<PostDetailDto> listFavorite(@RequestParam("userId") Long userId) {
        Member user = memberService.findOne(userId);
        List<Favorite> favorites = user.getFavorites();

        List<Post> posts = new ArrayList<>();

        for(Favorite favorite : favorites){

            Long postId = favorite.getPost().getPostId();

            posts.add(postService.findPostByPostId(postId));

        }
                List<PostDetailDto> result = posts.stream()
                .map(p -> new PostDetailDto(p))
                .collect(Collectors.toList());

        return result;
    }

}
