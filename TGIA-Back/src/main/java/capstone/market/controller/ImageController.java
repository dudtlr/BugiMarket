package capstone.market.controller;

import capstone.market.domain.Image;
import capstone.market.filedata.UploadFile;
import capstone.market.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ImageController {
    private final FileService fileService;

    @GetMapping("/get_images_filename")
    public List<String> sendSavedImageList() {
        ArrayList<String> nameList = new ArrayList<>();
        List<Image> allImagesName = fileService.getAllImagesName();
        for (Image image : allImagesName) {
            nameList.add(image.getImageFilename());
        }
        return nameList;
    }

    @CrossOrigin("*")
    @PostMapping("/send_image_native")
    public void sendImageFromNative(@RequestBody MultipartFile image) throws IOException {

        System.out.println("file.getInputStream() = " + image.getInputStream());
//        System.out.println("post.getResource() = " + image.getResource());

//        UploadFile attachFile = fileService.storeFile(image);
        fileService.storeFile(image);
//        return attachFile.getStoreFileName();
    }

    @PostMapping("/image/send_images")
    public List<String> uploadImages(List<MultipartFile> images) throws IOException {
        // 파일 처리 작업 수행
        List<String> filenames = new ArrayList<>();


        List<Image> uploadFiles = fileService.storeFiles(images);
        for (Image uploadFile : uploadFiles) {
            filenames.add(uploadFile.getImageFilename());
        }
        return filenames;
    }

    @PostMapping("/image/send_image")

    public String uploadImage(MultipartFile image) throws IOException {
        Image imageFile = fileService.storeFile(image);
        return imageFile.getImageFilename();
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public String downloadImage(@PathVariable String filename) throws MalformedURLException {

        System.out.println("이미지 url 보내기 성공!!!");
        final UrlResource urlResource = new UrlResource("file:" + fileService.getFullPath(filename));
        System.out.println(urlResource.toString());
        return urlResource.toString();
    }



}
