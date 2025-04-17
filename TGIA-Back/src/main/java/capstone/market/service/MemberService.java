package capstone.market.service;

import capstone.market.domain.CollegeType;
import capstone.market.domain.DepartmentType;
import capstone.market.domain.Member;
import capstone.market.profile_dto.ProfileListDto;
import capstone.market.repository.ImageRepository;
import capstone.market.repository.MemberDataJpa;
import capstone.market.repository.MemberRepository;
import capstone.market.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberDataJpa memberDataJpa;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    // 회원 가입
    public Long join(Member member) {
        memberRepository.save(member);


        return member.getId();
    }

    public Member findMemberByPK(Long id) {
        Member member = memberDataJpa.findById(id).get();
        return member;
    }

    public Member setMemberImage(Long id, String image_filename) {
        Member member = memberRepository.findOne(id);
        member.setImage(imageRepository.findByImageFilename(image_filename));
        return member;
    }

    public Member findMemberByUserId(String user_id) {
        return memberRepository.findMemberByUserId(user_id);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    public ProfileListDto findMyProfileList(Long userId){
        Member user = memberRepository.findOne(userId);
        ProfileListDto profileListDto = new ProfileListDto(user);
        return profileListDto;
    }

    public ProfileListDto addCollege(ProfileListDto profileListDto){
        Member user = memberRepository.findOne(profileListDto.getMember_id());
        user.setFirst_department(profileListDto.getFirst_department());
        user.setSecond_department(profileListDto.getSecond_department());

        //@@@ 첫번째 컬리지

        if( profileListDto.getFirst_department() == DepartmentType.컴퓨터공학부 ||
                profileListDto.getFirst_department() == DepartmentType.기계전자공학부 ||
                profileListDto.getFirst_department() == DepartmentType.스마트경영공학부)
        {
            user.setFirst_college(CollegeType.IT공과대학);
        }


        if(profileListDto.getFirst_department() == DepartmentType.상상력인재학부 ||
                profileListDto.getFirst_department() == DepartmentType.문학문화콘텐츠학과 ||
                profileListDto.getFirst_department() == DepartmentType.IT융합공학부 ||
                profileListDto.getFirst_department() == DepartmentType.융합보안학과
        ){

            user.setFirst_college(CollegeType.창의융합대학);
        }


        if(profileListDto.getFirst_department() == DepartmentType.글로벌패션산업학부 ||
                profileListDto.getFirst_department() == DepartmentType.ICT디자인학부 ||
                profileListDto.getFirst_department() == DepartmentType.뷰티디자인매니지먼트학과
        ){

            user.setFirst_college(CollegeType.디자인대학);
        }

        if(profileListDto.getFirst_department() == DepartmentType.사회과학부
        ){


            user.setFirst_college(CollegeType.미래융합사회과학대학);
        }



        if(profileListDto.getFirst_department() == DepartmentType.크리에이티브인문학부
        ){

            user.setFirst_college(CollegeType.크리에이티브인문예술대학);
        }


        if(profileListDto.getFirst_department() == DepartmentType.호텔외식경영학과 ||
                profileListDto.getFirst_department() == DepartmentType.융합행정학과 ||
                profileListDto.getFirst_department() == DepartmentType.비즈니스컨설팅학과 ||
                profileListDto.getFirst_department() == DepartmentType.뷰티디자인학과 ||
                profileListDto.getFirst_department() == DepartmentType.ICT융합디자인학과
        ){


            user.setFirst_college(CollegeType.미래플러스대학);
        }


        if(profileListDto.getFirst_department() == DepartmentType.기초교양학부 ||
                profileListDto.getFirst_department() == DepartmentType.소양핵심교양학부 ||
                profileListDto.getFirst_department() == DepartmentType.자율교양학부
        ){


            user.setFirst_college(CollegeType.상상력교양대학);
        }


        //@@@@@@@@@@@@@@@ 222222


        if( profileListDto.getSecond_department() == DepartmentType.컴퓨터공학부 ||
                profileListDto.getSecond_department() == DepartmentType.기계전자공학부 ||
                profileListDto.getSecond_department() == DepartmentType.스마트경영공학부)
        {

            user.setSecond_college(CollegeType.IT공과대학);
        }


        if(profileListDto.getSecond_department() == DepartmentType.상상력인재학부 ||
                profileListDto.getSecond_department() == DepartmentType.문학문화콘텐츠학과 ||
                profileListDto.getSecond_department() == DepartmentType.IT융합공학부 ||
                profileListDto.getSecond_department() == DepartmentType.융합보안학과
        ){

            user.setSecond_college(CollegeType.창의융합대학);
        }


        if(profileListDto.getSecond_department() == DepartmentType.글로벌패션산업학부 ||
                profileListDto.getSecond_department() == DepartmentType.ICT디자인학부 ||
                profileListDto.getSecond_department() == DepartmentType.뷰티디자인매니지먼트학과
        ){

            user.setSecond_college(CollegeType.디자인대학);
        }

        if(profileListDto.getSecond_department() == DepartmentType.사회과학부
        ){


            user.setSecond_college(CollegeType.미래융합사회과학대학);
        }



        if(profileListDto.getSecond_department() == DepartmentType.크리에이티브인문학부
        ){

            user.setSecond_college(CollegeType.크리에이티브인문예술대학);
        }


        if(profileListDto.getSecond_department() == DepartmentType.호텔외식경영학과 ||
                profileListDto.getSecond_department() == DepartmentType.융합행정학과 ||
                profileListDto.getSecond_department() == DepartmentType.비즈니스컨설팅학과 ||
                profileListDto.getSecond_department() == DepartmentType.뷰티디자인학과 ||
                profileListDto.getSecond_department() == DepartmentType.ICT융합디자인학과
        ){


            user.setSecond_college(CollegeType.미래플러스대학);
        }


        if(profileListDto.getSecond_department() == DepartmentType.기초교양학부 ||
                profileListDto.getSecond_department() == DepartmentType.소양핵심교양학부 ||
                profileListDto.getSecond_department() == DepartmentType.자율교양학부
        ){


            user.setSecond_college(CollegeType.상상력교양대학);
        }






        ProfileListDto profileListDto2 = new ProfileListDto(user);


        return profileListDto2;
    }



}
