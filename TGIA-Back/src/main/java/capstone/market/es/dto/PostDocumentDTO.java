package capstone.market.es.dto;


import capstone.market.domain.CategoryType;
import capstone.market.domain.CollegeType;
import capstone.market.domain.DepartmentType;
import capstone.market.domain.LocationType;
import capstone.market.domain.Post;
import capstone.market.domain.StatusType;
import capstone.market.domain.TrackType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostDocumentDTO implements Serializable {

    private Integer postId;

    private String title;

    private Integer memberId;

    private Integer purchaseId;

    private String category;

    private String department;

    private String text;

    private Integer price;

    private Integer views;

    private Integer likes;

    private String locationType;

    private String locationText;

    private List<String> images = new ArrayList<>();

    private String createdDate;

    private String modifiedDate;

    private String itemName;

    private String college;

    private String track;

    private String statusType;




}
