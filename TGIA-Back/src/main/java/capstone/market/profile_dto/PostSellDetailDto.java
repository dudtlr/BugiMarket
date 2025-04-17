package capstone.market.profile_dto;

import capstone.market.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PostSellDetailDto {

    private Long post_id;
    private String title;
    private Long user_id;

    private Long buyer_id;
    private CategoryType category;
    private DepartmentType department;
    private String text;
    private Integer price;

    private Integer views;
    private Integer likes;
    private LocationType locationType;
    private String location_text;
    private List<String> images = new ArrayList<>();
    private String item_name; // 상품명

    private CollegeType college;


    private TrackType track;


    private StatusType statusType;

    public PostSellDetailDto(Post post) {
        this.post_id = post.getPostId();
        this.title = post.getPost_title();
        this.user_id = post.getWho_posted().getId();
        this.category = post.getCategoryType();
        this.text = post.getPost_text();
        this.price = post.getPrice();
        this.department = post.getDepartmentType();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.locationType = post.getLocationType();
        this.location_text = post.getLocation_text();
        this.buyer_id = post.getPurchased().getMember().getId();
        this.item_name = post.getItem_name();
        this.college = post.getCollege();
        this.track = post.getTrack();
        this.statusType = post.getStatus();

        if (post.getImages().isEmpty()) {
            images.add("hello world");
        } else {
            for (Image image : post.getImages()) {
                images.add(image.getImageFilename());
            }
        }
    }
}
