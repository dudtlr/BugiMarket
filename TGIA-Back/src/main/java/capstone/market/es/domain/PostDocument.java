package capstone.market.es.domain;

import capstone.market.domain.Post;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//@Builder
@Document(indexName = "post")
public class PostDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private Long post_id;

    @Field(type = FieldType.Text)
    @Nullable
    private String title;

    @Field(type = FieldType.Keyword)
    @Nullable
    private Long member_id;

    @Field(type = FieldType.Keyword)
    @Nullable
    private Long purchase_id;

    @Field(type = FieldType.Keyword)
    @Nullable
    private String category;

    @Field(type = FieldType.Keyword)
    @Nullable
    private String department;

    @Field(type = FieldType.Text)
    @Nullable
    private String text;

    @Field(type = FieldType.Integer)
    @Nullable
    private Integer price;

    @Field(type = FieldType.Integer)
    @Nullable
    private Integer views;

    @Field(type = FieldType.Integer)
    @Nullable
    private Integer likes;

    @Field(type = FieldType.Keyword)
    @Nullable
    private String locationType;

    @Field(type = FieldType.Text)
    @Nullable
    private String location_text;

    @Field(type = FieldType.Keyword)
    @Nullable
    private List<String> images = new ArrayList<>();

    @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis})
    @Nullable
    private LocalDateTime createdDate;

    @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis})
    @Nullable
    private LocalDateTime modifiedDate;

    @Field(type = FieldType.Text)
    @Nullable
    private String item_name;

    @Field(type = FieldType.Keyword)
    @Nullable
    private String college;

    @Field(type = FieldType.Keyword)
    @Nullable
    private String track;

    @Field(type = FieldType.Keyword)
    @Nullable
    private String statusType;


     public PostDocument(Post post){
        this.post_id = post.getPostId();
        this.college = post.getCollege().toString();
        this.category = post.getCategoryType().toString();
        this.title = post.getPost_title();
        this.text = post.getPost_text();
        this.member_id = post.getWho_posted().getId();
        this.price = post.getPrice();
        this.department = post.getDepartmentType().toString();
        this.location_text = post.getLocation_text();
        this.locationType = post.getLocationType().toString();
        this.item_name = post.getItem_name();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.statusType = post.getStatus().toString();
    }
/*public PostDocument(Post post) {
    System.out.println("Initializing PostDocument with Post data");

    // Post ID
    System.out.println("Post ID: " + post.getPostId());
    this.post_id = post.getPostId();

    // College
    if (post.getCollege() == null) {
        System.out.println("Warning: College is null");
    } else {
        System.out.println("College: " + post.getCollege());
        this.college = post.getCollege().toString();
    }

    // Category
    if (post.getCategoryType() == null) {
        System.out.println("Warning: CategoryType is null");
    } else {
        System.out.println("CategoryType: " + post.getCategoryType());
        this.category = post.getCategoryType().toString();
    }

    // Title
    System.out.println("Title: " + post.getPost_title());
    this.title = post.getPost_title();

    // Text
    System.out.println("Text: " + post.getPost_text());
    this.text = post.getPost_text();

    // Member ID
    if (post.getWho_posted() == null) {
        System.out.println("Warning: Who_posted is null");
    } else {
        System.out.println("Member ID: " + post.getWho_posted().getId());
        this.member_id = post.getWho_posted().getId();
    }

    // Price
    System.out.println("Price: " + post.getPrice());
    this.price = post.getPrice();

    // Department
    if (post.getDepartmentType() == null) {
        System.out.println("Warning: DepartmentType is null");
    } else {
        System.out.println("DepartmentType: " + post.getDepartmentType());
        this.department = post.getDepartmentType().toString();
    }

    // Location Text
    System.out.println("Location Text: " + post.getLocation_text());
    this.location_text = post.getLocation_text();

    // Location Type
    if (post.getLocationType() == null) {
        System.out.println("Warning: LocationType is null");
    } else {
        System.out.println("LocationType: " + post.getLocationType());
        this.locationType = post.getLocationType().toString();
    }

    // Item Name
    System.out.println("Item Name: " + post.getItem_name());
    this.item_name = post.getItem_name();

    // Created Date
    System.out.println("Created Date: " + post.getCreatedDate());
    this.createdDate = post.getCreatedDate();

    // Modified Date
    System.out.println("Modified Date: " + post.getModifiedDate());
    this.modifiedDate = post.getModifiedDate();

    // Views
    System.out.println("Views: " + post.getViews());
    this.views = post.getViews();

    // Likes
    System.out.println("Likes: " + post.getLikes());
    this.likes = post.getLikes();

    // Status Type
    if (post.getStatus() == null) {
        System.out.println("Warning: Status is null");
    } else {
        System.out.println("StatusType: " + post.getStatus());
        this.statusType = post.getStatus().toString();
    }
}*/

}