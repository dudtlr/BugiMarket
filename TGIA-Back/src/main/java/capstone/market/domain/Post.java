package capstone.market.domain;

import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data

public class Post extends BaseEntity {


//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "post_sequence_generator")
//    @TableGenerator(
//            name = "post_sequence_generator",
//            table = "post_sequence",
//            pkColumnName = "sequence_name",
//            valueColumnName = "next_val", // 시퀀스의 다음 값을 저장할 컬럼
//            pkColumnValue = "post_id", // 이 엔티티에서 사용할 시퀀스 이름
//            initialValue = 1,
//            allocationSize = 50
//    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member who_posted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private CategoryType categoryType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private DepartmentType departmentType;


    private CollegeType college;


    private TrackType track;

    private Integer price;

    private String post_title;

    private String post_text;



    private Integer views = 0;

    private Integer likes = 0;

    //위치
    private LocationType locationType;
    //세부사항
    private String location_text;


    //이포스트를 조회한 유저 리스트
    @ElementCollection
    private Set<Long> viewedUserIds = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "purchased_id")
    @Nullable
    private Purchased purchased;

    @OneToMany(mappedBy = "post")
    private List<ChatRoom> chatRooms = new ArrayList<>();


    @OneToMany(mappedBy = "post")
    //@Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 40)
    private List<Image> images = new ArrayList<>();

    private String item_name; // 상품명

    private StatusType status; //  판매중, 거래예약, 거래완료

    private String reviewType; // 후기완료


    public Post() {
    }

    public Post(Member who_posted) {
        this.who_posted = who_posted;
    }

    public Post(String post_title, String post_text) {
        this.post_title = post_title;
        this.post_text = post_text;
    }

    public Post(Member who_posted, Category category, Integer price, String post_title, String post_text) {
        this.who_posted = who_posted;
        //this.category = category;
        this.price = price;
        this.post_title = post_title;
        this.post_text = post_text;
//        this.image = image;
    }

    public void setImages(List<Image> images) {
        for (Image image : images) {
            image.setPost(this);
        }
    }
}
