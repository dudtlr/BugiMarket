package capstone.market.domain;


import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long BoardId;
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    @Enumerated(EnumType.STRING)
    private DepartmentType departmentType;
    @Enumerated(EnumType.STRING)
    private CollegeType collegeType;
    @Enumerated(EnumType.STRING)
    private LocationType locationType;
    private String post_title;

}
