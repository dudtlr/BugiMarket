package capstone.market.controller;

import capstone.market.domain.*;

import capstone.market.es.dto.PostDocumentDTO;
import capstone.market.es.repository.BoardDocumentRepository;
import capstone.market.post_dto.*;


import capstone.market.profile_dto.*;
import capstone.market.repository.BoardRepository;
import capstone.market.service.*;
import capstone.market.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * react native 와 연동시 Controller 가 아닌 RestController 로 해야한다!
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostController {
    private final PostService postService;
    // post 를 작성한 Member 의 PK 를 알아내기 위해 memberService 사용
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final ImageService imageService;
    private final DepartmentService departmentService;

    private final BoardRepository boardRepository;
    private final BoardDocumentRepository boardDocumentRepository;

    private final SessionManager sessionManager;
    private final FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);


    @GetMapping("/board_search2")
    public List<BoardDocumentDto> SearchWithPaging3 (@RequestParam(required = false) List<String> categories,
                                                   @RequestParam(required = false) List<String> locations,
                                                   @RequestParam(required = false) List<String> departments,
                                                   @RequestParam(required = false) String keyword,
                                                   @RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "20") Integer size) throws IOException {

        //long startTime = System.nanoTime(); // 시작 시간 기록

        SearchFilterDto2 searchFilterDto = new SearchFilterDto2();
        searchFilterDto.setCategories(categories);
        searchFilterDto.setLocations(locations);
        searchFilterDto.setDepartments(departments);
        searchFilterDto.setKeyword(keyword);
        searchFilterDto.setPage(page);
        searchFilterDto.setSize(size);

        List<BoardDocumentDto> BoardDetailDtos = boardDocumentRepository.search(searchFilterDto);

//        long endTime = System.nanoTime(); // 종료 시간 기록
//        logger.info("SearchWithPaging2 execution time: {} ms", (endTime - startTime) / 1_000_000);

        return BoardDetailDtos;

    }

    @GetMapping("/board_search")
    public List<BoardDetailDto> SearchWithPaging2 (@RequestParam(required = false) List<CategoryType> categories,
                                                   @RequestParam(required = false) List<LocationType> locations,
                                                   @RequestParam(required = false) List<DepartmentType> departments,
                                                   @RequestParam(required = false) String keyword,
                                                   @RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "20") Integer size) {

        long startTime = System.nanoTime(); // 시작 시간 기록

        SearchFilterDto searchFilterDto = new SearchFilterDto();
        searchFilterDto.setCategories(categories);
        searchFilterDto.setLocations(locations);
        searchFilterDto.setDepartments(departments);
        searchFilterDto.setKeyword(keyword);
        searchFilterDto.setPage(page);
        searchFilterDto.setSize(size);

        List<BoardDetailDto> BoardDetailDtos = boardRepository.searchFilterWithPaging(searchFilterDto);

//        long endTime = System.nanoTime(); // 종료 시간 기록
//        logger.info("SearchWithPaging2 execution time: {} ms", (endTime - startTime) / 1_000_000);

        return BoardDetailDtos;

    }



    @GetMapping("/detailSearchWithPaging")
    public List<PostDetailDto> SearchWithPaging (SearchFilterDto searchFilterDto) {

        List<PostDetailDto> postDetailDtos = postService.searchFilterWithPaging(searchFilterDto);
        return postDetailDtos;
    }



    @PostMapping("/reservation_posts")
    public void reservatePosts(@RequestBody PostDetailDto postDetailDto){
        postService.findPostByPostId(postDetailDto.getPost_id()).setStatus(StatusType.거래예약);

    }

    @GetMapping("/get_status_type")
    public StatusType getStatusType(@RequestParam Long post_id){
       return postService.findPostByPostId(post_id).getStatus();
    }



    //@@@@@@@@@@@@@@@@@찐 필터링 구현@@@@@@@@@@@@@@@@@@@ 3월 23일
    //@@@@@@@@@@@@@@@@@카테고리로 포스트 필터링@@@@@@@@@@@@@@@@@@@ 3월 17일
    @GetMapping("/category")
    public List<PostListResponse> SearchByCategory(@RequestParam CategoryType category) {

        List<Post> posts = postService.SearchByCategory(category);

        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }
    //@@@@@@@@@@@@@@@@@카테고리로 포스트 필터링@@@@@@@@@@@@@@@@@@@ 3월 17일


    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@ 3월 15일
    @GetMapping("/search")
    public List<PostListResponse> findByTitleContaining(@RequestParam String keyword) {

        List<Post> posts = postService.findByTitleContaing(keyword);

        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }

    //@@@@@@@@@@@@@@@@@포스트 제목으로 검색하기 추가@@@@@@@@@@@@@@@@@@@ 3월 15일
    // 3월 18일 추가
    // 게시물을 수정하기 위한 기존 정보 가져온다.
    @GetMapping("post/edit")
    public PostForm updateItemForm(Long post_id) {
        Post post = postService.findPostByPostId(post_id);

        // form 을 업데이트 하는데 이 PostForm 을 보낸다 entity 가 아니라
        PostForm editPost = new PostForm();
//        editPost.setId(post.getPostId());
        editPost.setPrice(post.getPrice());
        editPost.setTitle(post.getPost_title());
        editPost.setContent(post.getPost_text());
        return editPost;
    }

    // 3월 18일 추가
    // 게시글 수정
    @PutMapping("post/edit")
    public PostForm updatePost(@RequestBody PostForm request) {
        postService.update(request);
        return new PostForm(request);
    }


    public List<PostListResponse> postListV4(String user_id) {
        log.info("@GetMapping(\"/post/list\")");
//        while(!SessionConst.POST_ENDED) {
//            log.info("WHILE");
//        }
        log.info("BREAK");
        log.info("user_id = {}", user_id);

        List<Post> posts = postService.findPostByUserId(user_id);

        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }

    //@@@@@@@@@@@@@@@@@@@@@@내가 올린 게시글 == 내가 판매중인 게시글 @@@@@@@@@@@@
    @GetMapping("/post/my_list") // 2.17 -> 판매중인 목록
    @Nullable
    public List<PostDetailDto> findMyPostList(@RequestParam Long userId) {

        Member findMember = memberService.findOne(userId);

        List<Post> posts = postService.findPostByUserId(findMember.getUser_id());


        List<Post> myListPosts = new ArrayList<>();

        for (Post post : posts) {

            if (post.getPurchased() == null) {
                myListPosts.add(postService.findPostByPostId(post.getPostId()));
            }
        }
        List<PostDetailDto> result = myListPosts.stream()
                .map(p -> new PostDetailDto(p))
                .collect(Collectors.toList());

        return result;
    }
    //@@@@@@@@@@@@@@@@@@@@@@내가 올린 게시글 == 내가 판매중인 게시글 @@@@@@@@@@@@

    //@@@@@@@@@@@@@@@@@@@@@@내가 판매 완료한 게시글 @@@@@@@@@@@@
    @GetMapping("/post/my_SellList") //
    public List<PostSellDetailDto> findMySellPostList(@RequestParam Long userId) {

        Member findMember = memberService.findOne(userId);

        List<Post> posts = postService.findPostByUserId(findMember.getUser_id());


        List<Post> mySellListPosts = new ArrayList<>();

        for (Post post : posts) {

            if (post.getPurchased() != null) {
                mySellListPosts.add(postService.findPostByPostId(post.getPostId()));
            }
        }


        List<PostSellDetailDto> result = mySellListPosts.stream()
                .map(p -> new PostSellDetailDto(p))
                .collect(Collectors.toList());

        return result;
    }




    @GetMapping("/post/list_all") // 2.17
    public List<PostListResponse> postListV6() {

        List<Post> posts = postService.findAll();

        List<PostListResponse> result = posts.stream()
                .map(p -> new PostListResponse(p))
                .collect(Collectors.toList());

        return result;
    }

    // 3월 17일 프론트와 연동 시 Image 테이블과 Post 테이블 매핑 문제 해결
    @PostMapping("/post/insert")
    public void postAdd(@RequestBody AddPostRequest request) {
        Post post = new Post();

        //log.info("request_info = {}", request.getUser_id());
        //log.info("request_info.title = {}", request.getTitle());
        post.setWho_posted(memberService.findOne(request.getUser_id()));
        post.setPost_title(request.getTitle());
        post.setPost_text(request.getContent());
        post.setPrice(request.getPrice());
        Category category = new Category();
        categoryService.UpdateCategory(category, request.getCategory());
        post.setCategoryType(request.getCategory());
        Department department = new Department();
        departmentService.UpdateDepartment(department, request.getDepartment());
        post.setDepartmentType(request.getDepartment());
        post.setItem_name(request.getItem_name());
        //System.out.println("requestadsfadfaf = " + request.getLocationType());
        post.setLocation_text(request.getLocation_text());
        post.setLocationType(request.getLocationType());
        post.setStatus(StatusType.판매중);

        post.setTrack(request.getTrack());
        //post.getDepartment().setDepartmentType(request.getDepartment());

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









        List<Image> images = imageService.findImages(request.getImages());
//        postService.setImage(post, images);
        post.setImages(images);
///        post.setImage(fileService.findImageFilename(request.image_file_name));
        postService.savePost(post);
    }

    @PostMapping("/post/insert_image_test")
    public void postAddImage(@RequestBody PostRequestImage request) {
        Post post = new Post();

        log.info("request_info = {}", request.getUser_id());
        log.info("request_info.title = {}", request.getTitle());
        post.setWho_posted(memberService.findOne(request.getUser_id()));
        post.setPost_title(request.getTitle());
        post.setPost_text(request.getContent());
        post.setPrice(request.getPrice());
//        Category category = new Category();
//        categoryService.UpdateCategory(category,request.getCategory());
//        post.setCategory(category);
//        post.setImage(fileService.findImageFilename(request.image_file_name));
        List<Image> images = imageService.findImages(request.getImages());
        post.setImages(images);
        postService.savePost(post);
    }

    // 게시물 상세 구현 2월 21일
    // + 가격 추가 3월 3일
    @GetMapping("/post/details")
    public PostDetailResponse postDetails(@RequestParam Long postId, @RequestParam Long userId) {
        Post post = postService.findPostByPostId(postId);

        postService.increaseViewCount(postId,userId);
//        List<Post> posts = togetherViewedService.add(post, postId);

        return new PostDetailResponse(post);
    }




    // hhhhhh
    @GetMapping("/post/get_info")
    public PostDetailResponse postInfo(Long post_id) {
        Post post = postService.findPostByPostId(post_id);

//        postService.increaseViewCount(postId, userId);

        return new PostDetailResponse(post);
    }

    //테스트용@@@@@@2
    @GetMapping("/post/all")
    public List<PostDetailDto> postDetails2() {
        List<Post> all = postService.findAll();


        List<Post> myListPosts = new ArrayList<>();

        for (Post post : all) {

            if (post.getPurchased() == null) {
                myListPosts.add(postService.findPostByPostId(post.getPostId()));
            }
        }
        List<PostDetailDto> result = myListPosts.stream()
                .map(p -> new PostDetailDto(p))
                .collect(Collectors.toList());


        return result;

    }


    @GetMapping("/post/all3")
    public List<PostDetailDto> getPostAllByPaging (@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "50") int size) {
        List<Post> all = postService.findAllPaged(page, size);

        List<Post> myListPosts = new ArrayList<>();

        for (Post post : all) {

            if (post.getPurchased() == null) {
                myListPosts.add(postService.findPostByPostId(post.getPostId()));
            }
        }

        List<PostDetailDto> result = myListPosts.stream()
                .map(p -> new PostDetailDto(p))
                .collect(Collectors.toList());

        return result;
    }
    //테스트용@@@@@@2

    // 찜 목록 구현 2월 21일
//    @PostMapping("/post/liked")
//    public void postLike(Long post_id, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        memberService.addLiked(post_id, member.getId());
//    }

//    @GetMapping("/post/liked")
//    public List<PostLikedResponse> postLikedList() {
//
//    }

    // 게시물 상세 화면을 위한 dto

//    @Data
//    static class PostDetailResponse {
//        private Long post_id;
//        private String title;
//        private String user_id;
//        private CategoryType category;
//        private DepartmentType department;
//        private String text;
//        private Integer price;
//
//        private Integer views;
//        private Integer likes;
//        private LocationType locationType;
//        private String location_text;
//
//        public PostDetailResponse(Post post) {
//            this.post_id = post.getPostId();
//            this.title = post.getPost_title();
//            this.user_id = post.getWho_posted().getUser_id();
//            this.category = post.getCategory().getCategory_type();
//            this.text = post.getPost_text();
//            this.price = post.getPrice();
//            this.department = post.getDepartment().getDepartmentType();
//            this.views = post.getViews();
//            this.likes = post.getLikes();
//            this.locationType = post.getLocationType();
//            this.location_text = post.getLocation_text();
//        }
//    }


//    static class PostLikedResponse {
//        private
//    }

    // 게시글 추가를 위한 FORM 객체

    /**
     * params: {
     *   title: title,
     *   user: id,
     *   category: category,
     *   content: content,
     *   date: time,
     *  },
     */

//    @Data
//    static class AddPostRequest {
//        private String title;
//        private Long user_id;
//        private CategoryType category;
//
//        private DepartmentType department;
//        private String content;
////        private String time;
//        private Integer price;
//
////        private String image_file_name;
//    }
//    //
//    @Data
//    static class ForUserId {
//        private String user_id;
//    }

//    @Data
//    static class PostListResponse {
////        private Long id;
//        private String title;
//        // private String user;
//        private CategoryType category;
////        private String content;
//        private Integer price;
//        private String image_filename;
//
//
//        public PostListResponse(Post post) {
//            title = post.getPost_title();
//            // user = post.getWho_posted().getUser_id();
//            category = post.getCategory().getCategory_type();
////            content = post.getPost_text();
//            price = post.getPrice();
//            //image_filename = post.getImage().getImageFilename();
//        }
//    }
//    @GetMapping("/post/list")
//    public PostListResponse postList() {
//        postService.findPostByUserId();
//    }
//
//    public Member findMe() {
//
//    }

}
