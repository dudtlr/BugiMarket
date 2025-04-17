package capstone.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Department {
    @Id @GeneratedValue
    @Column(name = "department_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    protected DepartmentType departmentType;

    public Department() {
    }


    public Department(DepartmentType departMentType) {
        this.departmentType = departMentType;
    }
}
