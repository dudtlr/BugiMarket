package capstone.market.repository;

import capstone.market.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    public Image findByImageFilename(String image_file_name);
}
