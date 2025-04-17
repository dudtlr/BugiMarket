package capstone.market.controller;

import capstone.market.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DeepLearnController {
    private final FileService fileService;

    private String deepLearn;

    @PostMapping("/send-data")
    public String sendYolov5(@RequestBody String data) {
        System.out.println("deepLearn: " + data);
        deepLearn = data;

        return data;
    }

    @GetMapping("/send-data")
    public String getYolov5() {
        if (deepLearn == null) {
            return "도서";
        }

        String objectName = deepLearn.substring(1, deepLearn.length() - 1);
        if (objectName == null) {
            return "생활가전";
        }
//        System.out.println("deepLearn: " + objectName);
        if (objectName.equals("laptop") || objectName.equals("cell phone") || objectName.equals("airpod")) {
            return "전자기기";
        } else if (objectName.equals("bicycle")) {
            return "생활가전";
        } else if (objectName.equals("clock") || objectName.equals("backpack")) {
            return "패션의류";
        } else if (objectName.equals("book")) {
            return "도서";
        } else if (objectName.equals("cosmetic")) {
            return "뷰티미용";
        } else if (objectName.equals("clothes") || objectName.equals("Wallet")) {
            return "패션의류";
        } else if (objectName.equals("bugi")) {
            return "부기굿즈";
        } else {
            return "뷰티미용";
        }
    }
}
