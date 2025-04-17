package capstone.market.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@MappedSuperclass
@Getter
@Setter
//@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
//    @CreatedDate

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();

//    @CreatedDate
//    private LocalDateTime createdDate;
//
//    @LastModifiedDate
//    private LocalDateTime modifiedDate;

    public void generateRandomCreatedDate() {
        // Generate random month within the range of 1 (January) to 12 (December)
        int randomMonth = ThreadLocalRandom.current().nextInt(1, 5);

        // Generate random day within the range of 1 to 28 (assumes a non-leap year)
        int randomDay = ThreadLocalRandom.current().nextInt(1, 29);
        this.createdDate = LocalDateTime.of(2023, randomMonth, randomDay, 0, 0);
    }
}
