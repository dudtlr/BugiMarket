package capstone.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class FirstTrack {
    @Id @GeneratedValue
    private Long track_id;
    //private String track_name;
    @Enumerated(EnumType.STRING)
    private TrackType first_track;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department departMent;

    public FirstTrack() {
    }

    public FirstTrack(TrackType first_track, Department departMent) {
        this.first_track = first_track;
        this.departMent = departMent;
    }
}
