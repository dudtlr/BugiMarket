package capstone.market.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDocumentDto implements Serializable {

    private String id;
    private String postTitle;
    private String categoryType;
    private String departmentType;
    private String collegeType;
    private String locationType;
    private String createdDate;
    private String modifiedDate;


}
