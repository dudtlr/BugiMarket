package capstone.market.controller;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.domain.TrackType;
import capstone.market.service.MemberService;
import capstone.market.service.PostService;
import capstone.market.session.SessionConst;
import capstone.market.session.SessionManager;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PostService postService;
    private final SessionManager sessionManager;

    //@PostMapping("/members/new")
    public void create(@RequestBody CreateMemberRequest request) {
        Member member = new Member();
        member.setUser_id(request.getUser_id());

        memberService.join(member);
    }

    // @PostMapping("/members/new")
    public void createV2(@RequestBody CreateMemberRequest request, HttpServletResponse response) {
        Member member = new Member();
        member.setUser_id(request.getUser_id());

        // 세션 관리자를 통해 세션을 생성하고, 회원 데이터 보관
        sessionManager.createSession(member, response);

        memberService.join(member);
    }



    @GetMapping("/member/get_image")
    public String memberProfileName(Long member_id) {
        Member member = memberService.findMemberByPK(member_id);
        return member.getImage().getImageFilename();
    }

    // userId
    @GetMapping("/member/get")
    public MemberResponseDTO getMember(@RequestParam String user_id) {
        Member member = memberService.findMemberByUserId(user_id);
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setUsername(member.getUsername());
        memberResponseDTO.setFirsttrack(member.getFirstTrack().getFirst_track());
        memberResponseDTO.setSecondtrack(member.getSecondTrack().getSecond_track());
        memberResponseDTO.setUser_id(member.getUser_id());
        memberResponseDTO.setMember_id(member.getId());
        memberResponseDTO.setImageFilename(member.getImage().getImageFilename());
        return memberResponseDTO;
    }

    @GetMapping("/member/getFirstTrack")
    public String getMemberFirstTrack(@RequestParam Long member_id) {
        Member member = memberService.findOne(member_id);
        return member.getFirstTrack().getFirst_track().toString();
    }

    @PostMapping("/members/new")
    public void createV3(@RequestBody CreateMemberRequest request, HttpServletRequest httpServletRequest) {

        log.info("@PostMapping(\"/members/new\")");

        Member member = new Member();
        member.setUser_id(request.getUser_id());

        Post post = new Post();
        post.setPost_title("hello world1");
        post.setWho_posted(member);

        Post post2 = new Post();
        post2.setPost_title("hello world2");
        post2.setWho_posted(member);

        memberService.join(member);

        postService.savePost(post);
        postService.savePost(post2);

//         세션 관리자를 통해 세션을 생성하고, 회원 데이터 보관
        HttpSession session = httpServletRequest.getSession();
        if (session == null) {
            log.info("session is null ;;");
        }
        else {
            log.info("session is not null?");
        }
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
//        SessionConst.POST_ENDED = true;
    }

    @GetMapping("/member/get_username")
    public String getUsername(Long id) {
        Member member = memberService.findOne(id);
        return member.getUsername();
    }

    // @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @Data
    static class CreateMemberRequest{
        private String user_id;
    }

    @Data
    static class getUserIdDTO {
        private String user_id;
    }

    @Data
    static class MemberResponseDTO {
        private Long member_id;
        private String user_id;
        private String username;
        private TrackType firsttrack;
        private TrackType secondtrack;
        private String imageFilename;



        public MemberResponseDTO() {
        }

        public MemberResponseDTO(String user_id, String username, TrackType firsttrack, TrackType secondtrack) {
            this.user_id = user_id;
            this.username = username;
            this.firsttrack = firsttrack;
            this.secondtrack = secondtrack;
        }
    }
}
