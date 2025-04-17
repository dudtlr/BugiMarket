package capstone.market.profile_dto;

import lombok.Data;

@Data
public class ProfileChangeDTO {
    private Long member_id;
    private String username;
    private String image_filename;

    public ProfileChangeDTO() {
    }

    public ProfileChangeDTO(Long member_id, String username, String image_filename) {
        this.member_id = member_id;
        this.username = username;
        this.image_filename = image_filename;
    }
}
