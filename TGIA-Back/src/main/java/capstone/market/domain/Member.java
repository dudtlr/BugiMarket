package capstone.market.domain;

import capstone.market.repository.CategoryJpaRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @OneToOne
    private Manner manner;
    private Integer mannerscore = 455;
    private String user_id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "track_id")
//    private Track track;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a_track_id")
    private FirstTrack firstTrack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_track_id")
    private SecondTrack secondTrack;

    private String username;

    @OneToMany(mappedBy = "memberB", fetch = FetchType.LAZY)
    private List<ChatRoom> chatRoom = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Favorite> favorites = new ArrayList<>();

    // 3월 17일 이미지 테이블 매핑 추가
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;
    private DepartmentType first_department;

    private DepartmentType second_department;

    private CollegeType first_college;

    private CollegeType second_college;
    private String createdDate;


    public Member() {
    }

    public Member(String user_id) {
        this.user_id = user_id;
    }
}