package capstone.market.controller;

import capstone.market.domain.Member;
import capstone.market.profile_dto.ProfileListDto;
import capstone.market.service.MemberService;
import capstone.market.service.PostService;
import capstone.market.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestProfileController {
    private final MemberService memberService;
    private final PostService postService;
    private final TrackService trackService;


    @GetMapping("/testing/profile")
    public ProfileListDto findMyProfileList(@RequestParam Long userId) {
        Member findmember = memberService.findOne(userId);
        ProfileListDto profileListDto = new ProfileListDto(findmember);
        return profileListDto;
    }
}
