package capstone.market.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class SecondTrack {
    @Id @GeneratedValue
    private Long track_id;
    // private String track_name;

    @Enumerated(EnumType.STRING)
    private TrackType second_track;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department departMent;

    public SecondTrack(TrackType second_track, Department departMent) {
        this.second_track = second_track;
        this.departMent = departMent;
    }
}
