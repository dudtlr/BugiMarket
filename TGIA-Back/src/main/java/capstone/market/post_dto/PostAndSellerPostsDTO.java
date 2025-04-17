package capstone.market.post_dto;

import capstone.market.profile_dto.PostDetailDto;
import lombok.Data;

import java.util.List;

@Data
public class PostAndSellerPostsDTO {
    private PostDetailDto currentPost;
    private List<PostDetailDto> sellerPosts;

    private List<PostDetailDto> PostsByCategory;

    public PostAndSellerPostsDTO(PostDetailDto currentPost, List<PostDetailDto> sellerPosts,List<PostDetailDto> PostsByCategory) {
        this.currentPost = currentPost;
        this.sellerPosts = sellerPosts;
        this.PostsByCategory = PostsByCategory;
    }
}
