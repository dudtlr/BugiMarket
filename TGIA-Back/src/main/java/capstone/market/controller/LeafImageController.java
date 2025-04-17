package capstone.market.controller;

import capstone.market.domain.Image;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.filedata.UploadFile;
import capstone.market.service.FileService;
import capstone.market.service.MemberService;
import capstone.market.service.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LeafImageController {

    //    private final ItemRepository itemRepository;
    private final FileService fileService;
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form) {
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
//        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
//        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        Member memberC = new Member("memberC");
        memberC.setUsername("건희");
        memberService.join(memberC);

        List<Image> uploadFiles = fileService.storeFiles(form.getImageFiles());

        Post post = new Post();
        post.setWho_posted(memberC);
        post.setPrice(20000);
        post.setPost_title("testing image");
        post.setPost_text("testing contents");
        post.setImages(uploadFiles);
        postService.savePost(post);

        //데이터베이스에 저장
//        Item item = new Item();
//        item.setItemName(form.getItemName());
//        item.setAttachFile(attachFile);
//        item.setImageFiles(storeImageFiles);
//        itemRepository.save(item);

//        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/{itemId}";
    }

//    @GetMapping("/items/{id}")
//    public String items(@PathVariable Long id, Model model) {
//        Item item = itemRepository.findById(id);
//        model.addAttribute("item", item);
//        return "item-view";
//    }

    @Data
    static class ItemForm {

        private Long itemId;
        private String itemName;
        private MultipartFile attachFile;
        private List<MultipartFile> imageFiles;
    }
}
