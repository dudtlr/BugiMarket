package capstone.market.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


    @Data
    @Document(indexName = "boards")
    public class BoardDocument extends BaseEntity{
        @Id
        private String id;
        private String postTitle;
        private String categoryType;
        private String departmentType;
        private String collegeType;
        private String locationType;

        public BoardDocument(Board board) {
            this.postTitle = board.getPost_title();
            this.categoryType = board.getCategoryType().name();
            this.departmentType = board.getDepartmentType().name();
            this.collegeType = board.getCollegeType().name();
            this.locationType = board.getLocationType().name();
        }
    }

