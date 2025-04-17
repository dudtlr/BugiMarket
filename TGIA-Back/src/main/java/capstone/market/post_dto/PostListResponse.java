package capstone.market.post_dto;

import capstone.market.domain.CategoryType;
import capstone.market.domain.Post;
import lombok.Data;

@Data
public class PostListResponse {
    //        private Long id;
    private String title;
    // private String user;
    private CategoryType category;
    //        private String content;
    private Integer price;
    private String image_filename;


    public PostListResponse(Post post) {
        title = post.getPost_title();
        // user = post.getWho_posted().getUser_id();
//        category = post.getCategory().getCategory_type();
//            content = post.getPost_text();
        price = post.getPrice();
//        image_filename = post.getImage().getImageFilename();
        if (post.getImages().isEmpty()) {
            image_filename = "null";
        } else {
            image_filename = post.getImages().get(0).getImageFilename();
        }
    }
}