package capstone.market.post_dto;

import lombok.Data;

import java.util.List;

@Data
public class PostRequestImage {
    private String title;
    private Long user_id;
    private String content;
    private Integer price;
    private List<String> images;
}