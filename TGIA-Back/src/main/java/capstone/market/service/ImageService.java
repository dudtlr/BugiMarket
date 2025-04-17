package capstone.market.service;

import capstone.market.domain.Image;
import capstone.market.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image findImageByName(String image_name) {
        Image image = imageRepository.findByImageFilename(image_name);
        return image;
    }

    public List<Image> findImages(List<String> image_names) {
        List<Image> images = new ArrayList<>();
        for (String imageName : image_names) {
            images.add(imageRepository.findByImageFilename(imageName));
        }
        return images;
    }
}
