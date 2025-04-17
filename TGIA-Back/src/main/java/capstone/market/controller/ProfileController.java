package capstone.market.controller;

import capstone.market.domain.*;
import capstone.market.post_dto.PostDetailResponse;
import capstone.market.post_dto.PostListResponse;
import capstone.market.profile_dto.*;
import capstone.market.service.ImageService;
import capstone.market.service.MemberService;
import capstone.market.service.PostService;
import capstone.market.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProfileController {
    private final MemberService memberService;
    private final PostService postService;
    private final TrackService trackService;
    private final ImageService imageService;



    //이게 지금 dto 널값 오류로 이게 안되고 아래꺼가 된다.
    @GetMapping("/post/buy_list")
    public  List<PostDetailResponse> getBuyerList(@RequestParam Long userId) {

        List<Post> buyList = postService.findBoughtListByUserId(userId);
        List<PostDetailResponse> result = buyList.stream()
                .map(p -> new PostDetailResponse(p))
                .collect(Collectors.toList());

        return result;

    }

    @GetMapping("/post/buy_list/evaluate")
    public void evaluateMember (@RequestBody EvaluationDto evaluationDto) {

        Integer evaluation = evaluationDto.getEvaluation();
        Member findMember = memberService.findOne(evaluationDto.getSell_userId());
        findMember.setMannerscore(findMember.getMannerscore() + evaluation);
        memberService.join(findMember);

    }

    //@@@@테스트용  성공 유저의 구매리스트
    @GetMapping("/post/buy_list3")
    public  List<PostListResponse> getBuyerList3(@RequestParam Long userId) {

        List<Post> buyList = postService.findBoughtListByUserId(userId);
        List<PostListResponse> result = buyList.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;

    }
    //@@@테스트

    @GetMapping("/profile")
    public ProfileListDto findMyProfileList(@RequestParam Long userId){


        return memberService.findMyProfileList(userId);


    }

    @PostMapping("/profile/add_college")
    public ProfileListDto addCollege(@RequestBody ProfileListDto profileListDto){


        return memberService.addCollege(profileListDto);

    }

    // 3월 17일
    // 프로필 이미지 변경
    @PostMapping("/profile/image_change")
    public void setProfileImage(@RequestBody ProfileImageChangeDTO profileImageChangeDTO) {
        memberService.setMemberImage(profileImageChangeDTO.getMember_id(), profileImageChangeDTO.getImage_filename());
    }

    @PostMapping("/profile/change")
    public ProfileListDto setProfile(@RequestBody ProfileChangeDTO profileChangeDTO) {
        memberService.setMemberImage(profileChangeDTO.getMember_id(), profileChangeDTO.getImage_filename());
        Member member = memberService.findMemberByPK(profileChangeDTO.getMember_id());
        member.setUsername(profileChangeDTO.getUsername());
        log.info("hello world 324234234= {}", profileChangeDTO.getImage_filename());
        member.setImage(imageService.findImageByName(profileChangeDTO.getImage_filename()));
        memberService.join(member);
        ProfileListDto profileListDto = new ProfileListDto(member);
        return profileListDto;
    }

    //트랙 설정 및 업데이트
    @PostMapping("/profile/list/")
    public ProfileListDto UpdateTrack(@RequestBody TrackUpdateDto trackUpdateDto) {
        //1. 멤버를 찾는다
        Member user = memberService.findOne(trackUpdateDto.getUserId());
        System.out.println("trackUpdateDto = " + trackUpdateDto);

        //2. 트랙넘버에 따라 1트랙 2트랙으로 경우를 나눈다.
        if(trackUpdateDto.getTrackNumber() == 1){
        FirstTrack firstTrack = trackService.findFirstTrackByid(trackUpdateDto.getTrackId());
        trackService.saveFirstTrack(firstTrack,TrackType.valueOf(trackUpdateDto.getTrackname()));
        user.setFirstTrack(firstTrack);
        user.setFirst_department(trackUpdateDto.getDepartmentType());
        memberService.join(user);
    }
        if(trackUpdateDto.getTrackNumber() == 2){
            SecondTrack secondTrack = trackService.findSecondTrackByid(trackUpdateDto.getTrackId());
            trackService.saveSecondTrack(secondTrack,TrackType.valueOf(trackUpdateDto.getTrackname()));
            user.setSecondTrack(secondTrack);
            user.setSecond_department(trackUpdateDto.getDepartmentType());
            memberService.join(user);
        }

        return new ProfileListDto(user);
//        return "redirect:/profile";

       // 숫자로 1트랙 2트랙 판단한다. 0/1

        //1트랙인지, 멤버 아이디 , 트랙 아이디 필요함
    }
}
