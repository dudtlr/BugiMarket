package capstone.market.profile_dto;

import capstone.market.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDetailDto {

    private Long BoardId;
    private CategoryType categoryType;
    private DepartmentType departmentType;
    private CollegeType collegeType;
    private LocationType locationType;
    private String post_title;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardDetailDto(Board board){
        this.BoardId = board.getBoardId();
        this.categoryType = board.getCategoryType();
        this.departmentType = board.getDepartmentType();
        this.collegeType = board.getCollegeType();
        this.locationType = board.getLocationType();
        this.post_title = board.getPost_title();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }



}
