package capstone.market.profile_dto;

import capstone.market.domain.TrackType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackDto {
    private TrackType TrackName;

    public TrackDto(TrackType trackName) {
        TrackName = trackName;
    }
}
