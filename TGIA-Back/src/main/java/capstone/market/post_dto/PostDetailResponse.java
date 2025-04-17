package capstone.market.post_dto;

import capstone.market.domain.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostDetailResponse {
    private String item_name; // 상품명
    private Long post_id;
    private Long user_id;
    private String title;
    private String writer;
    private CategoryType category;
    private String text;
    private Integer price;
    private List<String> images = new ArrayList<>();
    private Integer views;
    private Integer likes;
    private LocationType locationType;
    private String location_text;
    private Long member_id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private CollegeType college;
    private TrackType track;

    private String ReviewType;

//    private List<PostForm> postForms;


    public PostDetailResponse(Post post) {
        this.ReviewType = post.getReviewType();
        this.post_id = post.getPostId();
        this.title = post.getPost_title();
        this.member_id = post.getWho_posted().getId();
        this.writer = post.getWho_posted().getUsername();
        this.category = post.getCategoryType();
        this.text = post.getPost_text();
        this.price = post.getPrice();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.locationType = post.getLocationType();
        this.location_text = post.getLocation_text();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
        this.college = post.getCollege();
        this.track = post.getTrack();
        this.item_name = post.getItem_name();
//        for (PostForm postForm : postForms) {
//            posts.add(post);
//        }



        if (post.getImages().isEmpty()) {
            images.add("hello world");
        } else {
            for (Image image : post.getImages()) {
                images.add(image.getImageFilename());
            }
        }
    }
}
