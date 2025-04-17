package capstone.market.profile_dto;

import capstone.market.domain.CollegeType;
import capstone.market.domain.DepartmentType;
import capstone.market.domain.Member;
import capstone.market.domain.TrackType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public class ProfileListDto {

    private Long member_id;
    private String username;

    private TrackType firstTrack;

    private TrackType secondTrack;
    private String imageFileName;


    private Long AtrackId;

    private Long BtrackId;

    private Integer mannerscore;


    private DepartmentType first_department;

    private DepartmentType second_department;

    private CollegeType first_college;

    private CollegeType second_college;






    public ProfileListDto(Member member) {
        this.member_id = member.getId();
        this.username = member.getUsername();
        this.firstTrack = member.getFirstTrack().getFirst_track();
        this.secondTrack = member.getSecondTrack().getSecond_track();
        this.AtrackId = member.getFirstTrack().getTrack_id();
        this.BtrackId = member.getSecondTrack().getTrack_id();
        this.imageFileName = member.getImage().getImageFilename();
        this.mannerscore = member.getMannerscore();
        this.first_department = member.getFirst_department();
        this.second_department = member.getSecond_department();
        this.first_college = member.getFirst_college();
        this.second_college = member.getSecond_college();
    }
}
