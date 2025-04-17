package capstone.market.service;

import capstone.market.domain.*;
//import capstone.market.es.dto.PostDocumentDTO;
//import capstone.market.es.repository.PostDocumentElasticsearchRepository;
import capstone.market.post_dto.PostForm;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.profile_dto.SearchFilterDto;
import capstone.market.profile_dto.SearchFilterDto2;
import capstone.market.repository.*;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostDataJpaRepository postDataJpaRepository;

    private final SearchKeywordRepository searchKeywordRepository;

    //private final PostDocumentElasticsearchRepository postDocumentElasticsearchRepository;



    public List<Post> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return postDataJpaRepository.findAllByOrderByCreatedDateDesc(pageable);
    }



    public Post findPostByPostId(Long post_id) {
        return postRepository.findOne(post_id);
    }

    public void savePost(Post post) {
        postRepository.savePost(post);
    }

    public List<Post> findPostByUserId(String user_id) {
        return postRepository.findByUserId(user_id);
    }
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findBoughtListByUserId(Long user_id) {
        return postRepository.findBoughtListByUserId(user_id);
    }


    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@
    public List<Post> findByTitleContaing(String keyword){

        return postRepository.SearchByTitle(keyword);

    }
    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@

    //@@@@@@@@@@@@@@@@@카테고리로 필터링@@@@@@@@@@@@@@@@@@@
    public List<Post> SearchByCategory(CategoryType categoryType){

        return postRepository.SearchByCategory(categoryType);

    }
    //@@@@@@@@@@@@@@@@@카테고리로 필터링@@@@@@@@@@@@@@@@@@@


    // QueryDSL + Paging
    public List<PostDetailDto> searchFilterWithPaging(SearchFilterDto searchFilterDto){


//        if(searchFilterDto.getYs() !=1) {
//            SearchKeyword searchKeyword = searchKeywordRepository.findByKeyword(searchFilterDto.getKeyword());
//            if (searchKeyword == null) {
//                searchKeyword = new SearchKeyword();
//                searchKeyword.setKeyword(searchFilterDto.getKeyword());
//                searchKeyword.setSearchCount(1L);
//            } else {
//                searchKeyword.setSearchCount(searchKeyword.getSearchCount() + 1);
//            }
//            searchKeywordRepository.save(searchKeyword);
//
//        }

        return postDataJpaRepository.searchFilterWithPaging(searchFilterDto);


    }








    public void increaseViewCount(Long postId, Long userId){
        Post findPost = postRepository.findOne(postId);
        if(findPost != null){
            if(!findPost.getViewedUserIds().contains(userId)){
                findPost.setViews(findPost.getViews()+1);
                findPost.getViewedUserIds().add(userId);
                postRepository.savePost(findPost);
            }
        }
    }

    public List<PostDetailDto> findSellList(Long userId) {

        return postRepository.findSellList(userId);

    }

    public List<PostDetailDto> findListByCategory(CategoryType categoryType) {

       return postRepository.findListByCategory(categoryType);

    }



    // 5/8 이미지 설정 코드 수정
    public void setImage(Post post, List<Image> images) {
        post.setImages(images);
    }

    //필터링 추가 3/23 @@@@@@@@ final version

    public void randomDate(Post post) {
        // Generate random month within the range of 1 (January) to 12 (December)
        int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);

        // Generate random day within the range of 1 to 28 (assumes a non-leap year)
        int randomDay = ThreadLocalRandom.current().nextInt(1, 29);
//        post.setCreatedDate(LocalDateTime.of(2023, randomMonth, randomDay, 0, 0));
        post.setModifiedDate(LocalDateTime.of(2023, randomMonth, randomDay, 0, 0));
//        postRepository.savePost(post);
    }

    // 3월 18일 추가
    // 게시글 수정
    public void update(PostForm request) {
        Post post = postRepository.findOne(request.getId());
        post.setPost_title(request.getTitle());
        post.setPost_text(request.getContent());
        post.setPrice(request.getPrice());
        post.setLocation_text(request.getLocation_text());
        post.setLocationType(request.getLocationType());
        post.setItem_name(request.getItem_name());
        post.setTrack(request.getTrack());
//      postRepository.savePost(post);
        post.setStatus(request.getStatusType());
        post.setDepartmentType(request.getDepartment());

        if(request.getDepartment() == DepartmentType.컴퓨터공학부 ||
                request.getDepartment() == DepartmentType.기계전자공학부 ||
                request.getDepartment() == DepartmentType.스마트경영공학부){

            post.setCollege(CollegeType.IT공과대학);
        }


        if(request.getDepartment() == DepartmentType.상상력인재학부 ||
                request.getDepartment() == DepartmentType.문학문화콘텐츠학과 ||
                request.getDepartment() == DepartmentType.AI응용학과 ||
                request.getDepartment() == DepartmentType.융합보안학과
        ){

            post.setCollege(CollegeType.창의융합대학);
        }


        if(request.getDepartment() == DepartmentType.글로벌패션산업학부 ||
                request.getDepartment() == DepartmentType.ICT디자인학부 ||
                request.getDepartment() == DepartmentType.뷰티디자인매니지먼트학과
        ){

            post.setCollege(CollegeType.디자인대학);
        }

        if(request.getDepartment() == DepartmentType.사회과학부
        ){

            post.setCollege(CollegeType.미래융합사회과학대학);
        }



        if(request.getDepartment() == DepartmentType.크리에이티브인문학부
        ){

            post.setCollege(CollegeType.크리에이티브인문예술대학);
        }


        if(request.getDepartment() == DepartmentType.호텔외식경영학과 ||
                request.getDepartment() == DepartmentType.융합행정학과 ||
                request.getDepartment() == DepartmentType.비즈니스컨설팅학과 ||
                request.getDepartment() == DepartmentType.뷰티디자인학과 ||
                request.getDepartment() == DepartmentType.ICT융합디자인학과
        ){

            post.setCollege(CollegeType.미래플러스대학);
        }


        if(request.getDepartment() == DepartmentType.기초교양학부 ||
                request.getDepartment() == DepartmentType.소양핵심교양학부 ||
                request.getDepartment() == DepartmentType.자율교양학부
        ){

            post.setCollege(CollegeType.상상력교양대학);
        }
    }
}
