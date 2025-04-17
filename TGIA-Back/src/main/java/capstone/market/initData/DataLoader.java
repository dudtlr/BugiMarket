//package capstone.market.initData;
//
//import capstone.market.bugigram.User;
//import capstone.market.bugigram.UserJpaRepository;
//import capstone.market.bugigram.UserPost;
//import capstone.market.bugigram.UserPostJpaRepository;
//import capstone.market.domain.*;
//import capstone.market.es.domain.PostDocument;
//import capstone.market.es.repository.PostDocumentRepository;
//import capstone.market.repository.*;
//import capstone.market.service.ChatService;
//import capstone.market.service.ImageService;
//import capstone.market.service.PostService;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.concurrent.ThreadLocalRandom;
//
//@Component
//@Slf4j
//public class DataLoader {
//    @Autowired
//    private PostService postService;
//    @Autowired
//    private MannerRepository mannerRepository;
//
//    @Autowired
//    private UserPostJpaRepository userPostJpaRepository;
//    @Autowired
//    private ImageRepository imageRepository;
//    @Autowired
//    private UserJpaRepository userJpaRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private PostRepository postRepository;
//    @Autowired
//    private PurchasedRepository purchasedRepository;
//    @Autowired
//    private DepartMentJpaRepository departMentJpaRepository;
//    //    @Autowired
////    private ChatRoomRepository chatRoomRepository;
////    @Autowired
////    private ChatMessageRepository chatMessageRepository;
//    @Autowired
//    private PostDataJpaRepository postDataJpaRepository;
//    @Autowired
//    private ImageService imageService;
//    @Autowired
//    private FirstTrackJpaRepository firstTrackJpaRepository;
//    @Autowired
//    private SecondTrackJpaRepository secondTrackJpaRepository;
//    @Autowired
//    ChatService chatService;
//    @Autowired
//    private CategoryJpaRepository categoryJpaRepository;
//    @Autowired
//    private FavoriteJpaRepository favoriteJpaRepository;
//    @Autowired
//    private PostDocumentRepository postDocumentRepository;
//    List<Post> posts = new ArrayList<>();
//
//    private static final String[] lastNames = {
//            "김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "오", "한", "신", "서", "권",
//            "황", "안", "송", "류", "홍", "전", "고", "문", "양", "손", "배", "조", "백", "허", "유"
//    };
//
//    private static final String[] firstNames = {
//            "민준", "서준", "예준", "도윤", "시우", "주원", "하준", "지호", "지후", "준서", "준우", "현우",
//            "예준", "건우", "민재", "유준", "민성", "유찬", "현준", "민규", "우진", "재훈", "태민", "은우",
//            "영민", "성민", "주현", "민호", "동현", "태현", "승우", "성준", "예성", "동준", "동혁", "민우",
//            "예성", "재원", "은호", "준혁", "서진", "민찬", "윤우", "현서", "현성", "준영", "승민", "동우"
//    };
//
//    private static final String[] salePhrases = {
//            "팔아요", "팔께요", "팔아 버립니다", "사주세요", "진짜 싸게 파는 건데", "팝니다!", "팔아요!",
//            "싸게 드려요", "합리적인 가격에 팝니다", "빠른 판매 부탁드려요", "저렴한 가격으로 팔아요", "꼭 사세요!",
//            "최신 상품이에요", "많이 사용하지 않았어요", "마음에 드셔요", "인기 상품입니다", "마감 임박 상품입니다",
//            "한정 수량입니다", "놓치지 마세요!", "가성비 최고에요", "무료 배송합니다", "양도합니다",
//            "상태 최상입니다", "미개봉 제품입니다", "고급 소재로 만들었어요", "종류 다양해요", "무료 증정품 있어요",
//            "빠른 거래 가능합니다", "추가 할인해드려요", "언제든지 문의주세요", "많은 관심 부탁드려요", "한 번 사용한 적 없어요",
//            "사용감 거의 없어요", "착용감이 좋아요", "대박 세일 중입니다", "포장 상태 좋아요", "조금만 더 기다려주세요",
//            "유명 브랜드 제품입니다", "빠르게 팔고 싶어요", "즉시 구매 가능합니다", "놓치지 않으셨나요?", "필요 없어져서 팝니다",
//            "바로 가져가세요", "추가 사진 보내드려요", "확실한 구매를 약속드려요", "퀄리티 좋아요", "가격 흥정 가능합니다",
//            "신상품입니다", "첫 손님에게 특별 혜택", "할인 이벤트 진행 중", "관심이 많아요", "언제든지 가격 조정 가능해요",
//            "성능 좋아요", "한 번 사용해보세요", "사용법 간단합니다", "마감 임박 상품이에요", "매우 저렴한 가격에 팝니다"
//    };
//
//
//    public static String generateRandomKoreanName() {
//        Random random = new Random();
//        String lastName = lastNames[random.nextInt(lastNames.length)];
//        String firstName = firstNames[random.nextInt(firstNames.length)];
//        return lastName + firstName;
//    }
//
//    /*
//    @PostConstruct
//    public void init() {
//        Member memberA = new Member("memberA");
//        Member memberB = new Member("memberB");
//
//        memberRepository.save(memberA);
//        memberRepository.save(memberB);
//
//        Post post = new Post(memberA);
//        post.setPost_title("hello");
//        post.setPrice(10000);
//        postRepository.savePost(post);
//    }
//    */
//    public LocalDateTime generateRandomCreatedDate() {
//        // Generate random month within the range of 1 (January) to 12 (December)
//        int randomMonth = ThreadLocalRandom.current().nextInt(1, 5);
//
//        // Generate random day within the range of 1 to 28 (assumes a non-leap year)
//        int randomDay = ThreadLocalRandom.current().nextInt(1, 29);
//        return LocalDateTime.of(2023, randomMonth, randomDay, 0, 0);
//    }
//
//    @PostConstruct
//    public void init() {
//
//        Member minkyu = new Member("minkyu");
//        minkyu.setUsername("민규");
//
//        Member gunhee = new Member("gunhee");
//        gunhee.setUsername("건희");
//
//        Member jys = new Member("jys");
//        jys.setUsername("영식");
//
//        Member brave = new Member("brave");
//        brave.setUsername("용기");
//
//
//
//
//
//        LocalDateTime now = LocalDateTime.now();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
//        String formattedDate = now.format(formatter);
//        minkyu.setCreatedDate(formattedDate);
//        brave.setCreatedDate(formattedDate);
//        gunhee.setCreatedDate(formattedDate);
//        jys.setCreatedDate(formattedDate);
//
//        Image image = new Image();
//        image.setImageFilename("basicprofile.png");
//        imageRepository.save(image);
//        CategoryType[] categories = CategoryType.values();
//        DepartmentType[] departmentTypes = DepartmentType.values();
//        Random random = new Random();
//
//        Department department = new Department(DepartmentType.컴퓨터공학부);
//        Department iotCombined = new Department(DepartmentType.IT융합공학부);
//        departMentJpaRepository.save(department);
//        departMentJpaRepository.save(iotCombined);
//
//        FirstTrack firstTrack = new FirstTrack(TrackType.웹공학트랙, department);
//        firstTrackJpaRepository.save(firstTrack);
//
//        FirstTrack firstTrack1 = new FirstTrack(TrackType.웹공학트랙, department);
//        firstTrackJpaRepository.save(firstTrack1);
//
//        SecondTrack secondTrack = new SecondTrack(TrackType.빅데이터트랙, department);
//        secondTrackJpaRepository.save(secondTrack);
//
//        SecondTrack secondTrack1 = new SecondTrack(TrackType.빅데이터트랙, department);
//        secondTrackJpaRepository.save(secondTrack1);
//
//        minkyu.setFirstTrack(firstTrack);
//        minkyu.setSecondTrack(secondTrack);
//        // minkyu.setImage(image1);
//
//        gunhee.setFirstTrack(firstTrack1);
//        gunhee.setSecondTrack(secondTrack1);
//
//        jys.setFirstTrack(firstTrack1);
//        jys.setSecondTrack(secondTrack1);
//
//        brave.setFirstTrack(firstTrack1);
//        brave.setSecondTrack(secondTrack1);
//
//        jys.setFirst_college(CollegeType.상상력교양대학);
//        brave.setFirst_college(CollegeType.디자인대학);
//
//        Image image1 = new Image();
//        image1.setImageFilename("aaa.png");
//
//        imageRepository.save(image1);
//
//        Image gunheeProfile = new Image();
//        gunheeProfile.setImageFilename("gunheeProfile.png");
//        imageRepository.save(gunheeProfile);
//
//        Image braveProfile = new Image();
//        braveProfile.setImageFilename("braveProfile.png");
//        imageRepository.save(braveProfile);
//
//        Image jysProfile = new Image();
//        jysProfile.setImageFilename("jysProfile.png");
//        imageRepository.save(jysProfile);
//
//        User user1 = new User();
//        user1.setName("전영식");
//        user1.setNickname("식이");
//        user1.setEmail("sik2@naver.com");
//        user1.setPassword("@Qwerqwer");
//        user1.setImageFilename(jysProfile.getImageFilename());
//        userJpaRepository.save(user1);
//
//        UserPost userPost1 = new UserPost();
//        userPost1.setUser(user1);
//        userPost1.setPostImageFilename("70_j.jpg");
//        userPost1.setIntroTextField("향수 샀다~~ 예쁘죠??");
//        userPost1.setLikeTextField("좋아요 89개");
//
//        userPostJpaRepository.save(userPost1);
//
//        UserPost userPost2 = new UserPost();
//        userPost2.setUser(user1);
//        userPost2.setPostImageFilename("67_j.jpeg");
//        userPost2.setIntroTextField("오늘도 부기 토트백 !!~~");
//        userPost2.setLikeTextField("좋아요 122개");
//
//        userPostJpaRepository.save(userPost2);
//
//        UserPost userPost3 = new UserPost();
//        userPost3.setUser(user1);
//        userPost3.setPostImageFilename("55_j.jpg");
//        userPost3.setIntroTextField("아이패드 샀다~~ 부럽지 애들아??");
//        userPost3.setLikeTextField("좋아요 139개");
//
//        userPostJpaRepository.save(userPost3);
//
//        UserPost userPost4 = new UserPost();
//        userPost4.setUser(user1);
//        userPost4.setPostImageFilename("9_j.jpeg");
//        userPost4.setIntroTextField("오늘 전독시 책만 읽었다...ㅋㅋㅋㅋㅋ");
//        userPost4.setLikeTextField("좋아요 512개");
//
//        userPostJpaRepository.save(userPost4);
//
//        UserPost userPost5 = new UserPost();
//        userPost5.setUser(user1);
//        userPost5.setPostImageFilename("1_j.jpg");
//        userPost5.setIntroTextField("오늘도 스프링 공부!!");
//        userPost5.setLikeTextField("좋아요 39개");
//
//        userPostJpaRepository.save(userPost5);
//
//
//        User user2 = new User();
//        user2.setName("차은우");
//        user2.setNickname("은우");
//        user2.setEmail("1@naver.com");
//        user2.setPassword("@Qwerqwer");
//        user2.setImageFilename("jj1.webp");
//        userJpaRepository.save(user2);
//
//        UserPost userPost6 = new UserPost();
//        userPost6.setUser(user2);
//        userPost6.setPostImageFilename("boggie1.png");
//        userPost6.setIntroTextField("부기 너무 귀여워!");
//        userPost6.setLikeTextField("좋아요 198개");
//
//        userPostJpaRepository.save(userPost6);
//
//
//
//
//        User user3 = new User();
//        user3.setName("이재훈");
//        user3.setNickname("재훈");
//        user3.setEmail("2@naver.com");
//        user3.setPassword("@Qwerqwer");
//        user3.setImageFilename("jj2.jpg");
//        userJpaRepository.save(user3);
//
//        UserPost userPost7 = new UserPost();
//        userPost7.setUser(user3);
//        userPost7.setPostImageFilename("bugi.png");
//        userPost7.setIntroTextField("부기 인형 너무 귀여워!!!!!!");
//        userPost7.setLikeTextField("좋아요 1249개");
//
//        userPostJpaRepository.save(userPost7);
//
//
//
//        User user4 = new User();
//        user4.setName("정해인");
//        user4.setNickname("해인");
//        user4.setEmail("3@naver.com");
//        user4.setPassword("@Qwerqwer");
//        user4.setImageFilename("jj3.webp");
//        userJpaRepository.save(user4);
//
//        UserPost userPost8 = new UserPost();
//        userPost8.setUser(user4);
//        userPost8.setPostImageFilename("elec3_1.jpeg");
//        userPost8.setIntroTextField("아이패드 샀다 애들아!!!!");
//        userPost8.setLikeTextField("좋아요 12개");
//
//        userPostJpaRepository.save(userPost8);
//
//
//        User user5 = new User();
//        user5.setName("이혜주");
//        user5.setNickname("혜주");
//        user5.setEmail("4@naver.com");
//        user5.setPassword("@Qwerqwer");
//        user5.setImageFilename("jj4.png");
//        userJpaRepository.save(user5);
//
//        UserPost userPost9 = new UserPost();
//        userPost9.setUser(user5);
//        userPost9.setPostImageFilename("brave_beauty_1_1.jpg");
//        userPost9.setIntroTextField("향수 이쁘지~~~~");
//        userPost9.setLikeTextField("좋아요 11232개");
//
//        userPostJpaRepository.save(userPost9);
//
//
//
//        User user6 = new User();
//        user6.setName("이주빈");
//        user6.setNickname("주빈");
//        user6.setEmail("5@naver.com");
//        user6.setPassword("@Qwerqwer");
//        user6.setImageFilename("jj5.jpg");
//        userJpaRepository.save(user6);
//
//        UserPost userPost10 = new UserPost();
//        userPost10.setUser(user6);
//        userPost10.setPostImageFilename("brave_beauty_1_2.jpg");
//        userPost10.setIntroTextField("새 향수 개봉~~~~");
//        userPost10.setLikeTextField("좋아요 23232개");
//
//        userPostJpaRepository.save(userPost10);
//
//
//
//        User user7 = new User();
//        user7.setName("마리오");
//        user7.setNickname("마리오");
//        user7.setEmail("6@naver.com");
//        user7.setPassword("@Qwerqwer");
//        user7.setImageFilename("jj6.jpg");
//        userJpaRepository.save(user7);
//
//        UserPost userPost11 = new UserPost();
//        userPost11.setUser(user7);
//        userPost11.setPostImageFilename("brave_beauty_3_1.jpg");
//        userPost11.setIntroTextField("오늘 플렉스 했다~~ 내 시계 어때!!");
//        userPost11.setLikeTextField("좋아요 12개");
//
//        userPostJpaRepository.save(userPost11);
//
//
//        User user8 = new User();
//        user8.setName("곰돌이");
//        user8.setNickname("곰돌이");
//        user8.setEmail("7@naver.com");
//        user8.setPassword("@Qwerqwer");
//        user8.setImageFilename("jj7.jpeg");
//        userJpaRepository.save(user8);
//
//        UserPost userPost12 = new UserPost();
//        userPost12.setUser(user8);
//        userPost12.setPostImageFilename("brave_book_3_1.jpg");
//        userPost12.setIntroTextField("오늘부터 공부....");
//        userPost12.setLikeTextField("좋아요 12개");
//
//        userPostJpaRepository.save(userPost12);
//
//
//        User user9 = new User();
//        user9.setName("김현수");
//        user9.setNickname("현수");
//        user9.setEmail("8@naver.com");
//        user9.setPassword("@Qwerqwer");
//        user9.setImageFilename("jj8.jpeg");
//        userJpaRepository.save(user9);
//
//        User user10 = new User();
//        user10.setName("돌고래");
//        user10.setNickname("고래");
//        user10.setEmail("9@naver.com");
//        user10.setPassword("@Qwerqwer");
//        user10.setImageFilename("jj9.png");
//        userJpaRepository.save(user10);
//
//        User user11 = new User();
//        user11.setName("임하람");
//        user11.setNickname("하람");
//        user11.setEmail("10@naver.com");
//        user11.setPassword("@Qwerqwer");
//        user11.setImageFilename("jj10.jpg");
//        userJpaRepository.save(user11);
//
//
//
//
//
//
//
//
//        jys.setImage(jysProfile);
//
//        gunhee.setImage(gunheeProfile);
//        minkyu.setImage(image1);
//        brave.setImage(braveProfile);
//
//
//
//        Manner mannerA = new Manner();
//        mannerRepository.save(mannerA);
//        minkyu.setManner(mannerA);
//
//        Manner mannerB = new Manner();
//        mannerRepository.save(mannerB);
//        gunhee.setManner(mannerB);
//
//        Manner mannerC = new Manner();
//        mannerRepository.save(mannerC);
//        jys.setManner(mannerC);
//
//        Manner mannerD = new Manner();
//        mannerRepository.save(mannerD);
//        brave.setManner(mannerD);
//
//        memberRepository.save(minkyu);
//        memberRepository.save(gunhee);
//        memberRepository.save(jys);
//        memberRepository.save(brave);
//        // user_id 가 memberA인 멤버의 트랙1: 웹공학트랙, 2트랙을 빅데이터트랙
//        // 프론트에서 pk id가 4인 멤버의 트랙1, 2를 물어본다면?
//
//        Member dummyMemberForSale = new Member("dummyForSale");
//        dummyMemberForSale.setUsername(generateRandomKoreanName());
//        Manner dummyMannerForSale = new Manner();
//        mannerRepository.save(dummyMannerForSale);
//        dummyMemberForSale.setManner(dummyMannerForSale);
//        dummyMemberForSale.setImage(image1);
//        dummyMemberForSale.setFirst_college(CollegeType.IT공과대학);
//        FirstTrack dummyFirstTrackForSale = new FirstTrack(TrackType.사물인터넷트랙, iotCombined);
//        SecondTrack dummySecondTrackForSale = new SecondTrack(TrackType.지능시스템트랙, iotCombined);
//        firstTrackJpaRepository.save(dummyFirstTrackForSale);
//        secondTrackJpaRepository.save(dummySecondTrackForSale);
//        dummyMemberForSale.setFirstTrack(dummyFirstTrackForSale);
//        dummyMemberForSale.setSecondTrack(dummySecondTrackForSale);
//        dummyMemberForSale.setCreatedDate(formattedDate);
//        memberRepository.save(dummyMemberForSale);
//
//        for (int i = 0;i<10;i++) {
//            Member dummyMember = new Member("dummy" + i);
//            dummyMember.setUsername(generateRandomKoreanName());
//            LocalDateTime randomCreatedDate = generateRandomCreatedDate();
//
//            DateTimeFormatter randomMemberDateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
//            String formatted = randomCreatedDate.format(randomMemberDateFormatter);
//            Manner dummyManner = new Manner();
//            mannerRepository.save(dummyManner);
//            dummyMember.setManner(dummyManner);
//            dummyMember.setImage(image1);
//            dummyMember.setFirst_college(CollegeType.IT공과대학);
//            FirstTrack dummyFirstTrack = new FirstTrack(TrackType.사물인터넷트랙, iotCombined);
//            SecondTrack dummySecondTrack = new SecondTrack(TrackType.지능시스템트랙, iotCombined);
//            firstTrackJpaRepository.save(dummyFirstTrack);
//            secondTrackJpaRepository.save(dummySecondTrack);
//            dummyMember.setFirstTrack(dummyFirstTrack);
//            dummyMember.setSecondTrack(dummySecondTrack);
//            dummyMember.setCreatedDate(formatted);
//            memberRepository.save(dummyMember);
//        }
//
//        Category post1category = new Category();
//        Category post2category = new Category();
//        Category post3category = new Category();
//        Category post4category = new Category();
//        Category post5category = new Category();
//
//        post1category.setCategory_type(CategoryType.도서);
//        post2category.setCategory_type(CategoryType.생활가전);
//        post3category.setCategory_type(CategoryType.전자기기);
//        post4category.setCategory_type(CategoryType.전자기기);
//        post5category.setCategory_type(CategoryType.뷰티미용);
//
//        categoryJpaRepository.save(post1category);
//        categoryJpaRepository.save(post2category);
//        categoryJpaRepository.save(post3category);
//        categoryJpaRepository.save(post4category);
//        categoryJpaRepository.save(post5category);
//
//        LocationType[] locations = LocationType.values();
//
////            our_memory_image1.setPost(post2);
//
//
//
//        Post post1 = new Post();
//        post1.setPost_title("제목1");
//        post1.setPost_text("내용");
////        Post post1 = new Post("제목1","내용");
//
//
//        Post javaBookPost = new Post();
//        javaBookPost.setPost_title("객지2 교재 팝니다!");
//        javaBookPost.setPost_text("메모 거의 없습니다!");
//
//        Post macBookPost = new Post("오머~ 맥북 프로 싸게 팔아요~","진짜 찐으로 싸게 파는 거라서 가격협상은 힙들어요..");
//        Post bugiPost = new Post("부기 굿즈 팔아요!","부기 팔아요 싸게 파는거에용");
//
//        post1.setWho_posted(minkyu);
//        javaBookPost.setWho_posted(minkyu);
//        macBookPost.setWho_posted(gunhee);
//        bugiPost.setWho_posted(brave);
//
////        macBookPost.generateRandomCreatedDate();
//
//        post1.setPrice(10000);
//        javaBookPost.setPrice(8000);
//        macBookPost.setPrice(900000);
//        bugiPost.setPrice(2000);
//
//        // Set the created date for macBookPost with the random month, day, and current year (2023)
//
//
//
//
//        // 방금방금
//        //구매목록 하나 만들기금
//        // 멤버 a가올린 포스트를 멤버 b가 사면서 포스트에 구매 목록에 멤버가 멤버b로 바뀜
//      /*  Purchased purchased = new Purchased();
//        purchased.setMember(gunhee);
//        purchasedRepository.save(purchased);
//        post1.setPurchased(purchased);
//        postRepository.savePost(post1);
//
//        Purchased purchased2 = new Purchased();
//        purchased2.setMember(brave);
//        purchasedRepository.save(purchased2);
//       macBookPost.setPurchased(purchased2);*/
//
//
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("영상편집 책 팝니다")
////                .setContext("국방부 진중문고까지 등재된 책입니다.")
////                .setItemName("프리미어 프로 교재")
////                .addImageFilename("geonhee_book1_1.jpeg")
////                .addImageFilename("geonhee_book1_2.jpeg")
////                .addImageFilename("geonhee_book1_3.jpeg")
////                .setPrice(15000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setLocationType(LocationType.상상관)
////                .setTrackType(TrackType.웹공학트랙)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("데이터마이닝 교재")
////                .setContext("제 인생 최고의 강의입니다. 꼭 들으세요.")
////                .setItemName("데이터마이닝 교재")
////                .addImageFilename("geonhee_book2_1.jpeg")
////                .addImageFilename("geonhee_book2_2.jpeg")
////                .addImageFilename("geonhee_book2_3.jpeg")
////                .setPrice(15000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setLocationType(LocationType.공학관)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("C++ 교재 팔아요")
////                .setContext("교재 싸게 팔아요")
////                .setItemName("프리미어 프로 교재")
////                .addImageFilename("geonhee_book3_1.jpeg")
////                .addImageFilename("geonhee_book3_2.jpeg")
////                .addImageFilename("geonhee_book3_3.jpeg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setLocationType(LocationType.미래관)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("히가시노 게이고 호숫가 살인사건")
////                .setContext("진짜 재밌습니다. 꼭 읽으세요.")
////                .setItemName("호숫가 살인사건")
////                .addImageFilename("geonhee_book4_1.jpeg")
////                .addImageFilename("geonhee_book4_2.jpeg")
////                .setPrice(27000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("쓸모없는 지식")
////                .setContext("쓸모없는 지식의 쓸모")
////                .setItemName("쓸모없는 지식의 쓸모")
////                .addImageFilename("geonhee_book5_1.jpeg")
////                .setPrice(8000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("아이폰 6s 로즈골드 팔아요")
////                .setContext("상태 A급, 128GB, 로즈골드 팝니다. 배터리 97% 입니다.")
////                .setItemName("iPhone 6s")
////                .addImageFilename("geonhee_digital1_1.jpeg")
////                .addImageFilename("geonhee_digital1_2.jpeg")
////                .setPrice(100000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.상관없음)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("닌텐도 스위치 팝니다.")
////                .setContext("정신차리고 공부하려고 합니다. 동숲 에디션입니다.")
////                .setItemName("닌텐도 스위치 동숲 에디션")
////                .addImageFilename("geonhee_digital2_1.jpeg")
////                .addImageFilename("geonhee_digital2_2.jpeg")
////                .addImageFilename("geonhee_digital2_3.jpeg")
////                .setPrice(250000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.풋살장)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("기계식 키보드 팔아요")
////                .setContext("더 좋은 거 선물 받아서 싸게 팔아요. 청축이고 예쁩니다.")
////                .setItemName("기계식 키보드")
////                .addImageFilename("geonhee_digital3_1.jpeg")
////                .addImageFilename("geonhee_digital3_2.jpeg")
////                .setPrice(20000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.낙산관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("버즈 플러스 팔게요")
////                .setContext("버즈 플러스 화이트 본체만 팔아요.")
////                .setItemName("버즈 플러스")
////                .addImageFilename("geonhee_digital4_1.jpeg")
////                .addImageFilename("geonhee_digital4_2.jpeg")
////                .addImageFilename("geonhee_digital4_3.jpeg")
////                .setPrice(50000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("게이밍 노트북 팝니다")
////                .setContext("레노버 게이밍 노트북 팔아요. 얼마 안 써서 깨끗해요.")
////                .setItemName("레노버 게이밍 노트북")
////                .addImageFilename("geonhee_digital5_1.jpeg")
////                .addImageFilename("geonhee_digital5_2.jpeg")
////                .addImageFilename("geonhee_digital5_3.jpeg")
////                .setPrice(750000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("샤오미 로봇청소기 팝니다.")
////                .setContext("샤오미 로봇청소기 팔아요. 말 잘 듣고 청소 열심히 하는 강아지같은 친구입니다.")
////                .setItemName("샤오미 로봇청소기")
////                .addImageFilename("geonhee_life1_1.jpeg")
////                .addImageFilename("geonhee_life1_2.jpeg")
////                .setPrice(130000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("전자레인지 20L 팝니다.")
////	.setContext("이사가야돼서 전자레인지 팝니다. 계란 한 번 돌려서 터진 거 말곤 문제 없습니다.")
////                .setItemName("전자레인지 20L")
////	.addImageFilename("geonhee_life2_1.jpeg")
////                .addImageFilename("geonhee_life2_2.jpeg")
////                .setPrice(80000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("선풍기 팔아요.")
////                .setContext("동그란 선풍기 팝니다. 청소해서 드릴 거니까 바로 쓰면 됩니다.")
////                .setItemName("선풍기")
////                .addImageFilename("geonhee_life3_1.jpeg")
////                .addImageFilename("geonhee_life3_2.jpeg")
////                .setPrice(65000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("브라운 s9 전기면도기")
////                .setContext("네고 안 합니다.")
////                .setItemName("브라운 s9 전기면도기")
////                .addImageFilename("geonhee_life4_1.jpeg")
////                .addImageFilename("geonhee_life4_2.jpeg")
////                .addImageFilename("geonhee_life4_3.jpeg")
////                .setPrice(17000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("오아 눈 마사지기 저렴하게 팝니다.")
////                .setContext("이거 쓰고 광명 찾았습니다. 꼭 써보세요.")
////                .setItemName("오아 눈 마사지기")
////                .addImageFilename("geonhee_life5_1.jpeg")
////                .addImageFilename("geonhee_life5_2.jpeg")
////                .addImageFilename("geonhee_life5_3.jpeg")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.AI응용학과)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.풋살장)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("미니언즈 볼펜 팔아요")
////                .setContext("미니언즈 볼펜 팝니다.")
////                .setItemName("미니언즈 볼펜")
////                .addImageFilename("geonhee_pencil1_1.jpeg")
////                .addImageFilename("geonhee_pencil1_2.jpeg")
////                .addImageFilename("geonhee_pencil1_3.jpeg")
////                .setPrice(5000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.AI응용학과)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.상관없음)
////                .build();
////
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("마일드라이너 형광펜 10개 8000원")
////                .setContext("색깔 잘 나옵니다.")
////                .setItemName("마일드라이너 형광펜")
////                .addImageFilename("geonhee_pencil2_1.jpeg")
////                .addImageFilename("geonhee_pencil2_2.jpeg")
////                .setPrice(8000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.AI응용학과)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("씰스티커 5종")
////                .setContext("다꾸 좋아하시면 사서 써보세요!")
////	.setItemName("씰스티커")
////                .addImageFilename("geonhee_pencil3_1.jpeg")
////                .setPrice(4000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.AI응용학과)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("펜더 연필꽂이 팔아요")
////                .setContext("귀여운 펜더로 책상을 깔끔하게 꾸며보세요!")
////	.setItemName("연필꽂이")
////                .addImageFilename("geonhee_pencil4_1.jpeg")
////                .addImageFilename("geonhee_pencil4_2.jpeg")
////                .setPrice(4000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.AI응용학과)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("산리오 연필 12자루 5000원")
////                .setContext("마이멜로디, 쿠로미, 시나몬롤, 폼폼푸린 있습니다.")
////                .setItemName("연필")
////                .addImageFilename("geonhee_pencil5_1.jpeg")
////                .addImageFilename("geonhee_pencil5_2.jpeg")
////                .addImageFilename("geonhee_pencil5_3.jpeg")
////                .setPrice(5000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.AI응용학과)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("코듀로이 셔츠 팝니다.")
////                .setContext("겨울 옷 정리하는 김에 하나 팝니다. 데일리로 무난하게 좋아요.")
////                .setItemName("셔츠")
////                .addImageFilename("geonhee_clothes1_1.jpeg")
////                .setPrice(55000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.AI응용학과)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("반팔티 급처.")
////                .setContext("작년에 예뻐보여서 샀는데 올해 보니까 영 아니라서 팔아요.")
////                .setItemName("반팔티")
////                .addImageFilename("geonhee_clothes2_1.jpeg")
////                .addImageFilename("geonhee_clothes2_2.jpeg")
////                .setPrice(2000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("귀염뽀짝 신발 팔아여")
////                .setContext("샀는데 생각보다 신발이 작아서 팝니다. 230이고 한 번도 안 신었어요")
////                .setItemName("신발")
////                .addImageFilename("geonhee_clothes3_1.jpeg")
////                .addImageFilename("geonhee_clothes3_2.jpeg")
////                .setPrice(47000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("아디다스 볼캡 급처")
////                .setContext("데일리로 무난하게 좋습니다.")
////                .setItemName("아디다스 볼캡")
////                .addImageFilename("geonhee_clothes4_1.jpeg")
////                .addImageFilename("geonhee_clothes4_2.jpeg")
////                .setPrice(13000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("점프슈트 팔아용")
////                .setContext("시원하고 편해요. 네고 없어용")
////                .setItemName("점프슈트")
////                .addImageFilename("geonhee_clothes5_1.jpeg")
////                .setPrice(89000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("샤넬 핸드크림 5.5")
////	.setContext("향 좋고 보습력 좋습니다. 원래 8만원 짜리인데 5.5에 팔아요")
////	.setItemName("샤넬 핸드크림")
////                .addImageFilename("geonhee_beauty1_1.jpeg")
////                .addImageFilename("geonhee_beauty1_2.jpeg")
////                .setPrice(55000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("클린 웜코튼 향수 1/3 정도 썼어요")
////                .setContext("제목대로입니다.")
////                .setItemName("향수")
////                .addImageFilename("geonhee_beauty2_1.jpeg")
////                .setPrice(55000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.풋살장)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("미스치프 지샥 콜라보 손목시계")
////                .setContext("생활기스 거의 없고 배터리 충분합니다.")
////                .setItemName("손목시계")
////                .addImageFilename("geonhee_beauty3_1.jpeg")
////                .addImageFilename("geonhee_beauty3_2.jpeg")
////                .addImageFilename("geonhee_beauty3_3.jpeg")
////                .setPrice(85000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("루이비통 모노그램 이클립스 반지")
////                .setContext("반지 예뻐요")
////                .setItemName("루이비통 반지")
////                .addImageFilename("geonhee_beauty4_1.jpeg")
////                .addImageFilename("geonhee_beauty4_2.jpeg")
////                .setPrice(380000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상관없음)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("쿨톤 화장품 급처 :)")
////	.setContext("화장품 정리하는 김에 급처해요!")
////	.setItemName("화장품")
////                .addImageFilename("geonhee_beauty5_1.jpeg")
////                .addImageFilename("geonhee_beauty5_2.jpeg")
////                .addImageFilename("geonhee_beauty5_3.jpeg")
////                .setPrice(25000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상관없음)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("한성대 마우스패드 팔아요")
////                .setContext("샀는데 단패드라 필요가 없네요")
////                .setItemName("마우스패드")
////                .addImageFilename("geonhee_bugi1_1.png")
////                .setPrice(5000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상관없음)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("상상부기 담요 팔아요")
////                .setContext("따뜻하고 부기가 귀여워요")
////                .setItemName("상상부기 담요")
////                .addImageFilename("geonhee_bugi2_1.png")
////                .setPrice(15000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("부기 텀블러 팔아요! 700ml")
////	.setContext("깨끗하게 썼고 생각보다 용량 커요")
////                .setItemName("부기 텀블러")
////                .addImageFilename("geonhee_bugi3_1.jpeg")
////                .setPrice(8000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("부기굿즈 슬리퍼 270")
////                .setContext("생각보다 푹신하고 튼튼해요")
////                .setItemName("슬리퍼")
////                .addImageFilename("geonhee_bugi4_1.png")
////                .addImageFilename("geonhee_bugi4_2.jpeg")
////                .setPrice(15000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.풋살장)
////                .build();
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("상상부기 포스트잇 팝니다")
////                .setContext("다꾸 목적으로 샀던건데 생각보다 꾸준히 안 해서 그냥 팔아요 몇 장 안 썼어요")
////                .setItemName("포스트잇")
////                .addImageFilename("geonhee_bugi5_1.png")
////                .setPrice(3000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.낙산관)
////                .build();
//
//
//
//
//        CollegeType[] collegeTypes = CollegeType.values();
////        post1.setCategory(post1category);
////        javaBookPost.setCategory(post2category);
////        macBookPost.setCategory(post3category);
////        bugiPost.setCategory(post4category);
////
////        post1.setDepartment(department);
////        javaBookPost.setDepartment(department);
////        macBookPost.setDepartment(department);
////        bugiPost.setDepartment(department);
//
//        post1.setLocationType(LocationType.공학관);
//        post1.setLocation_text("101호");
//        javaBookPost.setLocationType(LocationType.미래관);
//        javaBookPost.setLocation_text("102호");
//        macBookPost.setLocationType(LocationType.공학관);
//        macBookPost.setLocation_text("103호");
//        bugiPost.setLocationType(LocationType.풋살장);
//        bugiPost.setLocation_text("104");
//
//        post1.setItem_name("MacBook Pro 13");
//        javaBookPost.setItem_name("아이폰 14 프로 맥스 실버 256GB");
//        macBookPost.setItem_name("맥북 프로 14");
//        bugiPost.setItem_name("토비의 스프링 1편");
//
//        post1.setTrack(TrackType.게임그래픽디자인트랙);
//        post1.setCollege(CollegeType.미래융합사회과학대학);
//        Department department1 = new Department(DepartmentType.AI응용학과);
//        //post1.setDepartment(department1);
//
//
//        javaBookPost.setTrack(TrackType.경제금융투자트랙);
//        javaBookPost.setCollege(CollegeType.디자인대학);
//        Department department2 = new Department(DepartmentType.문학문화콘텐츠학과);
//        //javaBookPost.setDepartment(department2);
//
//        macBookPost.setTrack(TrackType.기업경제분석트랙);
//        macBookPost.setCollege(CollegeType.미래플러스대학);
//        Department department3 = new Department(DepartmentType.IT융합공학부);
//        //macBookPost.setDepartment(department3);
//
//        bugiPost.setTrack(TrackType.글로벌비즈니스트랙);
//        bugiPost.setCollege(CollegeType.IT공과대학);
//        Department department4 = new Department(DepartmentType.상상력인재학부);
//        //bugiPost.setDepartment(department4);
//
//        departMentJpaRepository.save(department1);
//        departMentJpaRepository.save(department2);
//        departMentJpaRepository.save(department3);
//        departMentJpaRepository.save(department4);
//
//        // 도서 1
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("토비의 스프링 세트 1,2권 팔아요")
////                .setContext("토비의 스프링 1권 2권 세트로 팝니다 \n 공부하려고 삿는데 시작도 못햇네요\n" +
////                        "\n" +
////                        "상태는 깨끗합니다 직거래만 하려고요\n" +
////                        "\n" +
////                        "직거래 공학관 1층에서 가능합니다\n" +
////                        "\n" +
////                        "쿨거래 하시면 에누리 해드립니다")
////                .setItemName("토비의스프링세트")
////                .addImageFilename("1_j.jpg")
////                .addImageFilename("2_j.jpg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //도서2 jpa
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("ORM 표준 JPA 프로그래밍 팔아요")
////                .setContext("사놓고 3번 아래로봤어요\n" +
////                        "\n" +
////                        "사진같은 파란볼펜 키워드만 밑줄친거 5-6장 내외있습니다\n" +
////                        "공학관2층 휴게실에서 거래 원합니다!!\n" +
////                        "쿨거래시 더 싸게 해드려요!")
////                .setItemName("ORM표준JPA프로그래밍")
////                .addImageFilename("3_j.jpg")
////                .addImageFilename("4_j.jpg")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //도서3 달러구트 백화점 1, 2
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("[새책] 달러구트 꿈백화점 1,2 2권 / 이미예 / 팩토리나인")
////                .setContext("책장에 보관만 했던 새책입니다.\n" +
////                        "\n" +
////                        "사진 필요시 찍어서 보내드려요.\n" +
////                        "\n" +
////                        "2권 정가 27,600원인데 18,000원에 가져 가세요.\n" +
////                        "\n" +
////                        "2권 일괄 거래 원합니다.\n" +
////                        "\n" +
////                        "새책이라 네고, 에눌은 어렵습니다.\n" +
////                        "\n" +
////                        "공학관 1층 상상파크 플러스에서 거래 원합니다!")
////                .setItemName("달러구트 꿈백화점 1,2 2권")
////                .addImageFilename("5_j.jpg")
////                .setPrice(18000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //도서4 해리포터
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("해리포터(구판) 50,000원에 판매합니다.")
////                .setContext("전집에서 세 권이 누락되어서 저렴하게 판매하고자 합니다.\n" +
////                        "- 불의잔 2,3권\n" +
////                        "\n" +
////                        "- 죽음의성물 1권\n" +
////                        "\n" +
////                        "\u200B\n" +
////                        "\n" +
////                        "직거래는 공학관1층 상플에서 원합니다~.\n" +
////                        "\n" +
////                        "언제든 문자 주세요!")
////                .setItemName("해리포터(구판)")
////                .addImageFilename("6_j.jpg")
////                .addImageFilename("7_j.jpg")
////                .addImageFilename("8_j.jpg")
////                .setPrice(18000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //도서5 전지적 독자시점
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("전지적독자시점 전권 1-14권 팝니다")
////                .setContext("한두권 살짝 뜯어진거 빼고 상태 최상입니다."+
////                        "\n" +
////                        "직거래는 공학관1층 상플에서 원합니다~.\n" +
////                        "\n" +
////                        "언제든 문자 주세요!")
////                .setItemName("전지적독자시점 전권 1-14")
////                .addImageFilename("9_j.jpeg")
////                .addImageFilename("10_j.jpeg")
////                .setPrice(130000)
////                .setCategoryType(CategoryType.도서)
////                .setLocationType(LocationType.공학관)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setDepartmentType(DepartmentType.컴퓨터공학부).build();
////
////
////
////        // 민규 전자기기 하드 코딩 7개
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("에어팟 프로 1세대, air pods pro")
////                .setContext("air pods pro 1세대 팝니다!")
////                .setItemName("에어팟")
////                .addImageFilename("minkyu0.jpeg")
////                .addImageFilename("minkyu1.jpeg")
////                .setPrice(108000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("National Geograpic air pod hard case")
////                .setContext("에어팟 하드 케이스 정품 2만원짜리 10000에\n" +
////                        "쿨거래 하실분?")
////                .setItemName("에어팟")
////                .addImageFilename("minkyu2.jpeg")
////                .addImageFilename("minkyu3.jpeg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("Air pods max")
////                .setContext("상처없고 깨끗합니다\n" +
////                        "구매하고 집에서만 몇번 사용해서 외관은\n" +
////                        "새거와 다르지 않아요")
////                .setItemName("에어팟")
////                .addImageFilename("minkyu4.jpeg")
////                .addImageFilename("minkyu5.jpeg")
////                .setPrice(450000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("에어팟 맥스(Airpods Max) 스그")
////                .setContext("22년 초쯤에 선물 받았는데\n" +
////                        "거의 사용하지 않아 판매합니다\uD83E\uDD79\n" +
////                        "\n" +
////                        "기스 없고 외관상 흠잡을 곳이 없습니다!\n" +
////                        "기능적으로는 문제 없는 것이 당연하구요!\n" +
////                        "그만큼도 안 썼기 때무니죠,,, \uD83E\uDD79\n" +
////                        "실사용 시간은 60시간도 안될 겁니다~\n" +
////                        "\n" +
////                        "박스있고 안에 구성품은 미사용입니다!\n" +
////                        "하지만 에어팟 커버? 집? 이 없어요 그 검은색 ㅠㅠ\n" +
////                        "\n" +
////                        "직거래는 이매/야탑/서현까지 조율 가능합니다!\n" +
////                        "택배비는 +3000원 우체국입니다~")
////                .setItemName("에어팟 맥스")
////                .addImageFilename("minkyu6.jpeg")
////                .addImageFilename("minkyu7.jpeg")
////                .addImageFilename("minkyu8.jpeg")
////                .setPrice(400000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("에어팟 프로1 풀셋 Apple Air Pod Pro 1")
////                .setContext("구매후 거의 쓰지않은 에어팟 프로1 입니다.\n" +
////                        "\n" +
////                        "케이스 포함이며 모든 유닛 정상 작동합니다.\n" +
////                        "\n" +
////                        "구매후 항상 케이스에 보관하고 있어 생활기스도 거의 없어요.\n" +
////                        "\n" +
////                        "본체와 왼쪽과 오른쪽 유닛외 다른 옵션은 없어요.\n" +
////                        "\n" +
////                        "필요하신 분께서 빠르게 가져가셔서 유용하게 쓰세요.\n" +
////                        "\n" +
////                        "선정릉역에서 직거래합니다\n" +
////                        "\n" +
////                        "감사합니다")
////                .setItemName("에어팟")
////                .addImageFilename("minkyu9.jpeg")
////                .addImageFilename("minkyu10.jpeg")
////                .addImageFilename("minkyu11.jpeg")
////                .addImageFilename("minkyu12.jpeg")
////                .setPrice(110000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("airpods pro 에어팟프로 케이스 새제품(개당)")
////                .setContext("airpods pro 에어팟프로 케이스 새제품\n" +
////                        "에어팟프로 케이스 새제품\n" +
////                        "마직막 사진 고양이 케이스 판매입니다\n" +
////                        "착용사진보시면 딱 맞게 들어갑니다~\n" +
////                        "묵직한 느낌이구요, 짱짱해서 보호잘됩니다\n" +
////                        "팟프로입니다~ 잘확인하시고 구매하세요\n" +
////                        "부경대정문 거래희망합니다\n" +
////                        "(강아지케이스 판매완료)")
////                .setItemName("에어팟 pro")
////                .addImageFilename("minkyu13.jpeg")
////                .addImageFilename("minkyu14.jpeg")
////                .addImageFilename("minkyu15.jpeg")
////                .setPrice(6000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("에즈원아이피씨 KBD_82UT 키보드 ")
////                .setContext("44,000원짜리 15,000원에 드립니다.")
////                .setItemName("에어팟")
////                .addImageFilename("minkyu16.jpeg")
////                .addImageFilename("minkyu17.jpeg")
////                .setPrice(15000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        // 도서 7개
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("쏙쏙 들어오는 인공지능 알고리즘")
////                .setContext("정말 깨끗합니다!")
////                .setItemName("쏙쏙 들어오는 인공지능 알고리즘")
////                .addImageFilename("minkyu57.jpeg")
////                .setPrice(20000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("Understanding of Database(데이터베이스의 이해)")
////                .setContext("BOSS 휴고보스 볼펜 노트 세트 새상품\n" +
////                        "박스에 세트로 담겨 있습니다")
////                .setItemName("Understanding of Database(데이터베이스의 이해)")
////                .addImageFilename("minkyu58.jpeg")
////                .setPrice(17000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("Advanced Engineering Mathematics")
////                .setContext("BOSS 휴고보스 볼펜 노트 세트 새상품\n" +
////                        "박스에 세트로 담겨 있습니다")
////                .setItemName("Advanced Engineering Mathematics")
////                .addImageFilename("minkyu59.jpeg")
////                .addImageFilename("minkyu60.jpeg")
////                .setPrice(20000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("신간도서 자기계발서 주식책 경제책 포토샵책 마케팅책 과학도서")
////                .setContext("상세 도서정보는 검색해보세요~!\n" +
////                        "모두 살짝 펼쳐보기만 한 정도의 새책이에요~~\n" +
////                        "권당 7.000원 ~ 10.000원")
////                .setItemName("신간도서 ")
////                .addImageFilename("minkyu61.jpeg")
////                .addImageFilename("minkyu62.jpeg")
////                .setPrice(7000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("수질환경기사책, 산업기사책")
////                .setContext("신권, 준비하다가 제 길이 아닌 것 같아 책 판매하고 떠납니다~")
////                .setItemName("필기구 세트")
////                .addImageFilename("minkyu63.jpeg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("나의 히어로 아카데미아 만화책 판매합니당! 나히아만화책!!!")
////                .setContext("나히아 만화책 입니당~\n" +
////                        "1권부터 32권까지 있어용~\n" +
////                        "몇번 읽다가 다른책 사려고 팔아용~~\n" +
////                        "32권은 개봉하지도 않았습니당~\n" +
////                        "많은 관심부탁드려용!")
////                .setItemName("필기구 세트")
////                .addImageFilename("minkyu64.jpeg")
////                .addImageFilename("minkyu65.jpeg")
////                .setPrice(80000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("원피스 만화책 1-39권, 레드, 블루")
////                .setContext("일괄 판매해요~ 요즘 제일 hot한 애니로는 정주행 절대 못해요\n책으로 읽으세요~")
////                .setItemName("필기구 세트")
////                .addImageFilename("minkyu66.jpeg")
////                .addImageFilename("minkyu67.jpeg")
////                .addImageFilename("minkyu68.jpeg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        // 필기구 7개
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("휴고보스 필기구 세트 새상품")
////                .setContext("BOSS 휴고보스 볼펜 노트 세트 새상품\n" +
////                        "박스에 세트로 담겨 있습니다")
////                .setItemName("필기구 세트")
////                .addImageFilename("minkyu18.jpeg")
////                .addImageFilename("minkyu19.jpeg")
////                .setPrice(45000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("스테들러 필기구 4종 멀티세트")
////                .setContext("스테들러 4종 멀티세트 입니다.\n" +
////                        "살 때 인터넷 최저가로 개당 5천원에 주고 샀어요~\n" +
////                        "구성품 확인해보시라고 인터넷상의 제품설명 사진에 첨부했어요~\n" +
////                        "6개 중 2개 파로 4개 남았습니다\n" +
////                        "개당 2500원에 팔고 4개 모두 한꺼번에 구매하시면 9000원에 드릴게요~\n" +
////                        "\n" +
////                        "부명초 앞 직거래 가능합니다\n" +
////                        "(사정상 평일 오후 8시 이후, 주말 아무 때나 가능)")
////                .setItemName("필기구")
////                .addImageFilename("minkyu20.jpeg")
////                .setPrice(2500)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("필기구 팝니당")
////                .setContext("이제 공부할일이 없어서 다 팝니다")
////                .setItemName("필기구")
////                .addImageFilename("minkyu21.jpeg")
////                .setPrice(5000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.사물인터넷트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("필기구 일괄 판매합니다")
////                .setContext("궁금하신점은 문자주세요")
////                .setItemName("필기구")
////                .addImageFilename("minkyu22.jpeg")
////                .addImageFilename("minkyu23.jpeg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("몽블랑 필기구와 장식품")
////                .setContext("몽블랑에서 나온 소소한 학용품들입니다.\n" +
////                        "\n" +
////                        "지우개 + 자 + 연필 3 자루 + 인형 입니다.\n" +
////                        "인형은 등에 클래식 만년필을 지고 있는데 디테일도 있고 참 예쁩니다.\n" +
////                        "\n" +
////                        "수원시청역 직거래 가능합니다.")
////                .setItemName("필기구")
////                .addImageFilename("minkyu24.jpeg")
////                .setPrice(23000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("고시용 필기구의 최강자! 에너겔 메탈팁 45개 판매")
////                .setContext("고시용 필기구에 최강자 펜텔 에너겔 메탈팁\n" +
////                        "검정 39, 빨강 3, 파랑 3\n" +
////                        "총 45자루입니다\n" +
////                        "\n" +
////                        "펜 팁 그대로 붙어 있는 새것 입니다\n" +
////                        "\n" +
////                        "자루당 1,000원 입니다\n" +
////                        "\n" +
////                        "연락주세요^^")
////                .setItemName("필기구")
////                .addImageFilename("minkyu25.jpeg")
////                .addImageFilename("minkyu26.jpeg")
////                .setPrice(45000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("크로스 볼펜 필기구 마블시리즈 헐크 cross rolling ball pen marvel hulk")
////                .setContext("cross rolling ball pen\n" +
////                        "\n" +
////                        "크로스 볼펜입니다.\n" +
////                        "마블 헐크 모델입니다.\n" +
////                        "새것과 같은 상태입니다.\n" +
////                        "\n" +
////                        "궁금하신 점은 전화주세요.")
////                .setItemName("필기구")
////                .addImageFilename("minkyu27.jpeg")
////                .addImageFilename("minkyu28.jpeg")
////                .addImageFilename("minkyu29.jpeg")
////                .setPrice(80000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        // 생활가전 7개
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("갖다드림) 레트로 강철 선풍기 가전제품 냉방용품 생활가전 ")
////                .setContext("레트로 스타일의 강철 선풍기 판매합니다\n" +
////                        "단단하고 잘 돌아갑니다\n" +
////                        "데코용으로 좋습니다\n" +
////                        "\n" +
////                        "노원 도봉 의정부\n" +
////                        "무료로 갖다드립니다")
////                .setItemName("선풍기")
////                .addImageFilename("minkyu44.jpeg")
////                .addImageFilename("minkyu45.jpeg")
////                .setPrice(40000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("생활가전")
////                .setContext("가습기대용량1대소형한대커피머신분쇄기일괄판매문의주세요")
////                .setItemName("가습기")
////                .addImageFilename("minkyu46.jpeg")
////                .setPrice(50000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("스웨덴명품가전 일렉트로룩스 전기주전자 무선포트 새상품")
////                .setContext("스웨덴명품가전 일렉트로룩스 전기주전자 무선포트\n" +
////                        "새상품")
////                .setItemName("전기 주전자")
////                .addImageFilename("minkyu47.jpeg")
////                .setPrice(50000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("생활가전) 공기청정기")
////                .setContext("공기 청정기. 잇어서. 하나는. 필요하신분. 가져가세요\n" +
////                        "요즘 미세먼지로 인환 호흡기. 불편하신분. 좋을거 같아요.\n" +
////                        "찔러보기 잠수 타시는분. 예민하신분 피해주시고 당근 특정 중고. 거래상 교환 환불 안됩니다")
////                .setItemName("공기청정기")
////                .addImageFilename("minkyu48.jpeg")
////                .addImageFilename("minkyu49.jpeg")
////                .addImageFilename("minkyu50.jpeg")
////                .setPrice(25000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("생활가전) 귀뚜라기 족욕기")
////                .setContext("구입해서 한번써고 방치했드니 중고 됐네요.귀뚜라미 족욕기에요")
////                .setItemName("귀뚜라미 족욕기")
////                .addImageFilename("minkyu51.jpeg")
////                .addImageFilename("minkyu52.jpeg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("무료배달) 락앤락 진공쌀통 10kg 쌀벌래방지 생활용품 생활가전제품")
////                .setContext("락앤락 진공쌀통 10kg\n" +
////                        "작동 잘 됩니다\n" +
////                        "외부 기스 조금 있으나\n" +
////                        "실사용에는 전혀 문제 없습니다\n" +
////                        "사진 참고해주세요\n" +
////                        "예민하신 분들은 사양합니다\n" +
////                        "\n" +
////                        "노원 도봉 의정부 무료배달")
////                .setItemName("락앤락 진공쌀통")
////                .addImageFilename("minkyu53.jpeg")
////                .addImageFilename("minkyu54.jpeg")
////                .addImageFilename("minkyu55.jpeg")
////                .setPrice(35000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("공기청정기제습기")
////                .setContext("하이마트에서 55 만원주고샀어요\n" +
////                        "안쓸것같아 싸겐가져가세요")
////                .setItemName("공기청정기제습기")
////                .addImageFilename("minkyu56.jpeg")
////                .setPrice(50000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        // 부기 굿즈 7개
////
////        // 뷰티 미용 7개
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("메이크업자격증, 미용자격증, 미용재료")
////                .setContext("연습용으로 사용하기 좋아요\n" +
////                        "메이크업 자격증 할 때 사용했어요~")
////                .setItemName("미용 용품")
////                .addImageFilename("minkyu30.jpeg")
////                .setPrice(25000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("미용대 미용의자 미용실샴푸의자 팔아요")
////                .setContext("작년에 중고로 40만원에 구매했어요 ~\n" +
////                        "\n" +
////                        "어머님 다리가 안 좋으셔서 몇 번 사용했다가 회복하셔서 판매합니다^^\n" +
////                        "\n" +
////                        "수도꼭지는 빼버려서 철물점에서 냉온수 가능한 걸로 따로구매하셔야 할 것 같아 싸게 올려둡니다^^\n" +
////                        "\n" +
////                        "직접 가져가셔야 하고요 SUV 차량엔 실릴 것 같네요 ~\n" +
////                        "\n" +
////                        "곤지암 오향리 입니다")
////                .setItemName("미용 용품")
////                .addImageFilename("minkyu31.jpeg")
////                .addImageFilename("minkyu32.jpeg")
////                .addImageFilename("minkyu33.jpeg")
////                .setPrice(70000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("강아지미용가위 바리깡 미용 해먹거치대 나들이띠 합")
////                .setContext("강아지입양받아 미용시키려고발등 구매하여 2회사용하고 다른데로 분양보내면서 필요없어져 새제품이나 다름없어요 필요하신분 직거래로 연락바람니다 사진참고하시면됩니다")
////                .setItemName("미용 용품")
////                .addImageFilename("minkyu34.jpeg")
////                .addImageFilename("minkyu35.jpeg")
////                .addImageFilename("minkyu36.jpeg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("향수 일괄로 싸게 처분합니다 포맨트 코튼허그 비레디 보이블랙")
////                .setContext("포맨트 코튼허그 50ml\n" +
////                        "비레디 보이블랙 50ml\n" +
////                        "일괄 사용감 10회미만이고 상태최상 입니다\n" +
////                        "동생이 향수를 쓰지않아 싸게 처분합니다\n" +
////                        "일괄로만 판매합니다")
////                .setItemName("뷰티미용")
////                .addImageFilename("minkyu37.jpeg")
////                .setPrice(45000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("빅토르앤롤프 플라워밤 향수+봉봉 오드 향수")
////                .setContext("구매해서 10번도 안뿌린 것 같아요!\n" +
////                        "향 좋습니다!!!! 근데 향수정리하면서 향수가 많아서 이 친구도 보내주려 합니다...\n" +
////                        "플러워밤(본품) + 봉봉(미니) 다 드립니다! 박스도 있어요!\n" +
////                        "\n" +
////                        "신대방삼거리역 거래가능\n" +
////                        "문고리 거래가능\n" +
////                        "네고가능")
////                .setItemName("필기구")
////                .addImageFilename("minkyu38.jpeg")
////                .addImageFilename("minkyu39.jpeg")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("크리드 향수공병 톰포드 향수공병")
////                .setContext("크리드 어벤투스 100ml 20,000\n" +
////                        "톰포드 네롤리 50ml 20,000\n" +
////                        "크리드 실버마운틴 75ml 20,000")
////                .setItemName("향수")
////                .addImageFilename("minkyu40.jpeg")
////                .setPrice(20000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("입생로랑 몽파리 오 드 뚜왈렛 루미에르 향수 30 미리 입생향수")
////                .setContext("선물받구 안써서 내놔요")
////                .setItemName("향수")
////                .addImageFilename("minkyu41.jpeg")
////                .addImageFilename("minkyu42.jpeg")
////                .addImageFilename("minkyu43.jpeg")
////                .setPrice(65000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////
////
////
////        //필기구1 파카 조터 스텐레스 신형 볼펜 미사용 새제품 2개 일괄 판매
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("파카조터 볼펜 세제품 2개 판매")
////                .setContext("파카 조터 스텐레스 볼펜 신형 미사용 새제품 2개 일괄로만 판매합니다.\n" +
////                        "\n" +
////                        "2만원에 판매합니다. 택배비별도 4천원\n" +
////                        "\n" +
////                        "\u200B\n" +
////                        "\n" +
////                        "본드 스트리트 블랙 CT 볼펜 \n" +
////                        "\n" +
////                        "빅토리아 바이올렛 CT 볼펜\n" +
////                        "\n" +
////                        "\u200B\n" +
////                        "\n" +
////                        "의 2개로 박스포함 풀세트입니다.\n" +
////                        "직거래는 상상관 1층에서 원합니다")
////                .setItemName("파카 조터 스텐레스 볼펜 신형")
////                .addImageFilename("11_j.jpg")
////                .addImageFilename("12_j.jpg")
////                .setPrice(20000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////
////        //필기구2 까르띠에 볼펜
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("까르띠에 볼펜 판매")
////                .setContext("시필만해서 상태 매우좋습니다\n" +
////                        "\n" +
////                        "구성품은 사진과 같습니다\n" +
////                        "\n" +
////                        "소모성 문자 자제해주세요\n" +
////                        "\n" +
////                        "상상관 1층에서 거래 원합니다! \n" +
////                        "쿨거래 시 더 싸게 해드려요!")
////                .setItemName("까르띠에 볼펜")
////                .addImageFilename("13_j.jpg")
////                .addImageFilename("14_j.jpg")
////                .addImageFilename("15_j.jpg")
////                .addImageFilename("16_j.jpg")
////                .setPrice(400000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        //필기구3 쿠로미 3색 볼펜
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("쿠로미 3색 볼펜 판매")
////                .setContext("짜잔!!\n" +
////                        "\n" +
////                        "귀여운 쿠로미 캐리어안에 소장각!!\n" +
////                        "\n" +
////                        "캐리어 뒤적이다보니 똑같은 볼펜이 두개 ㅡ.ㅡ\n" +
////                        "\n" +
////                        "쿠로미 하나씩 사들이는 재미가 있네용 ~\n" +
////                        "\n" +
////                        "\u200B\n" +
////                        "\n" +
////                        "조금 누워있다가 저녁 준비해야겠네유\n" +
////                        "\n" +
////                        "상상관 2층 팥고당에서 거래 원합니다!!")
////                .setItemName("쿠로미 3색 볼펜")
////                .addImageFilename("17_j.jpg")
////                .addImageFilename("18_j.jpg")
////                .setPrice(50000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////
////        //필기구4 산리오 3색볼펜
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("산리오 3색볼펜 판매해요~")
////                .setContext("마이멜로디 시나몬롤 한교동 포차코 쿠로미 캐릭터 있으며 3색 볼펜입니다\n" +
////                        "\n" +
////                        "\u200B\n" +
////                        "\n" +
////                        "모두 새상품입니다\n" +
////                        "\n" +
////                        "\u200B\n" +
////                        "\n" +
////                        "마이멜로디 6자루\n" +
////                        "\n" +
////                        "시나몬롤 8자루\n" +
////                        "\n" +
////                        "포차코 7자루\n" +
////                        "\n" +
////                        "쿠로미 5자루  \n" +
////                        "\n" +
////                        "남아있습니다")
////                .setItemName("산리오 3색 볼펜")
////                .addImageFilename("19_j.jpeg")
////                .setPrice(3000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////
////
////        //필기구5 디즈니스토어 프릭션 3색볼펜 (새제품)
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("디즈니스토어 프릭션 3색볼펜 (새제품) 판매")
////                .setContext("디즈니스토어 프릭션 3색볼펜 (새제품)\n" +
////                        "\n" +
////                        "지워지는 3색볼펜 (0.5mm)\n" +
////                        "\n" +
////                        "미사용 새제품 입니다\n" +
////                        "\n" +
////                        "각 15,000원\n" +
////                        "상상관 1층에서 거래 원합니다~")
////                .setItemName("디즈니스토어 프릭션 3색 볼펜")
////                .addImageFilename("20_j.jpg")
////                .setPrice(15000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////
////
////        //생활가전1
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("컴퓨터 책상 저렴히 판매합니다")
////                .setContext("컴퓨터 책상 저렴히 판매합니다\n" +
////                        "까사미아에서 약 50만원에 구매했습니다\n" +
////                        "가로 세로 높이 140 60 74로 2인이 사무작업하기 충분한 사이즈 입니다!\n" +
////                        "상태 너무 좋고 아주 잘 사용하고 있으나 2개월 후 프랑스 이민 계획이 있어 슬슬 정리합니다.\n" +
////                        "10만원에 판매합니다")
////                .setItemName("까사미아 컴퓨터 책상")
////                .addImageFilename("21_j.jpg")
////                .setPrice(100000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////        //생활가전2
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("필립스 HD9743/45 에어프라이어 판매")
////                .setContext("필립스 HD9743/45 에어프라이어 저렴히 판매합니다!\n" +
////                        "결혼하면서 구매하고 1년 정도 사용했는데 프랑스 이민 준비중이라 저렴히 판매합니다\n" +
////                        "거의 새것 같은 상태이고 비닐도 채 안뜯은 부분들도 있습니다.\n" +
////                        "구매 당시 할인 받아 25만원에 코스트코에서 구매했던 모델입니다\n" +
////                        "8만원에 판매합니다\n" +
////                        "미래관 지하 1층에서 거래 원합니다!!")
////                .setItemName("필립스 에어프라이어")
////                .addImageFilename("22_j.jpg")
////                .addImageFilename("23_j.jpg")
////                .setPrice(80000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////
////        //생활가전3
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("수아비 방역소독기 팔아요")
////                .setContext("방역 소독기 팝니다.\n" +
////                        "시중가는 387000이고요\n" +
////                        "새거 15만에 내놓습니다\n" +
////                        "직거래는 미래관 지하1 층에서 가능합니다\n")
////                .setItemName("수아비 방역소독기")
////                .addImageFilename("24_j.jpg")
////                .addImageFilename("25_j.jpg")
////                .setPrice(150000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////
////
////        //생활가전4
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("삼성 UHD 50인치 TV 판매")
////                .setContext("삼성 UHD 50인치 TV 저렴히 판매합니다\n" +
////                        "프랑스 이민 준비중이라 저렴히 판매합니다\n" +
////                        "패널교체 한번 했어서 흠집이나 불량화소 없이 새것 같은 상태이고 깨끗하게 사용했습니다.\n" +
////                        "해외 직구 등 A/S 불가한 상품 아닙니다 정식 서비스 전부 가능합니다\n" +
////                        "구매가 약 150 가량이나 40만원에 판매하겠습니다")
////                .setItemName("삼성 UHD 50인치 TV")
////                .addImageFilename("26_j.jpg")
////                .addImageFilename("27_j.jpg")
////                .setPrice(400000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////
////        //생활가전5
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("삼성 전자레인지 팔아요")
////                .setContext("삼성 전자레인지 3만원\n" +
////                        "저의 자취 생활과 함께한 전자레인지 입니다. \n" +
////                        "외식 고물가 시대에 집에서 밥해먹을려면 필수 입니다. \n" +
////                        "가전은 본 기능에만 충실하면 된다 생각합니다.\n" +
////                        "잘 작동됩니다.\n" +
////                        "미래관 지하 1층에서 거래 원합니다")
////                .setItemName("삼성 전자레인지")
////                .addImageFilename("28_j.jpg")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////
////
////        //패션의류 1
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("커스텀멜로우 반팔 셔츠 판매합니다")
////                .setContext("깔끔한 색상에 스티치 라인으로 포인트를 준 셔츠 입니다 \n" +
////                        "코튼 나일론 혼방소재로 매우 가볍고 여름에 시원하게 입으실 수 있습니다\n" +
////                                "구매 후 실착용 3회 미만으로 상태 매우 좋습니다\n" +
////                                "뒷면 아주 작은 한땀?정도 살짝 뜯김 있지만 아주 미세하고\n" +
////                                "작은 부위라 신경 쓰이는 정도는 아닙니다! \n" +
////                                "표기상 사이즈 95 입니다\n" +
////                                "(마른 100분들까지 입으실 수 있습니다)\n" +
////                                "색상은 어두운 네이비 입니다\n" +
////                                "직거래 인성관 1층에서 합니다!" )
////                .setItemName("커스텀멜로우 반팔 셔츠")
////                .addImageFilename("29_j.jpg")
////                .addImageFilename("30_j.jpg")
////                .addImageFilename("31_j.jpg")
////                .addImageFilename("32_j.jpg")
////                .setPrice(50000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.인성관)
////                .build();
////
////        //패션의류 2
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("하와이안 셔츠 반팔 판매합니다")
////                .setContext("NII 하와이안 셔츠 판매합니다\n" +
////                        "49,900원 택 제거 안 한 상태로 보관중입니다\n" +
////                        "표기상 사이즈 L 100 입니다\n" +
////                        "국내 남성 100- 마른 105까지 추천드립니다\n" +
////                        "직거래 인성관 1층에서 진행해요!")
////                .setItemName("하와이안 셔츠 반팔")
////                .addImageFilename("33_j.jpg")
////                .addImageFilename("34_j.jpg")
////                .addImageFilename("35_j.jpg")
////                .setPrice(20000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.인성관)
////                .build();
////
////        //패션의류 3나이키 쪼리 슬리퍼 판매합니다
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("나이키 쪼리 슬리퍼 판매합니다")
////                .setContext("미국 여행할 때 사왔던건데 안 신고 보관만 하고 있었던거라\n" +
////                        "\n" +
////                        "택만 제거 된 새상품 입니다\n" +
////                        "쿠션감 있는 쪼리라 인기 많았던 제품이에요\n" +
////                        "박스는 따로 없습니다\n" +
////                        "표기상 사이즈 240 입니다\n" +
////                        "직거래 인성관 1층에서 진행해요")
////                .setItemName("나이키 쪼리 슬리퍼")
////                .addImageFilename("36_j.jpg")
////                .addImageFilename("37_j.jpg")
////                .addImageFilename("38_j.jpg")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.디지털콘텐츠가상현실트랙)
////                .setLocationType(LocationType.인성관)
////                .build();
////
////
////        //패션의류 4나이키 쪼리 슬리퍼 판매합니다
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("새상품) 밀로 홀리데이 시그니처 볼캡")
////                .setContext("밀로 홀리데이 시그니처 볼캡 판매합니다\n" +
////                        "한개는 택 그대로 있고\n" +
////                        "나머지 한개는 택 제거만 된 새상품입니다\n" +
////                        "모자가 어울리는 얼굴형이 아니라서 판매해요\n" +
////                        "직거래 수원\n" +
////                        "택배비 4000원 별도\n" +
////                        "색상 파인그린, 차콜\n" +
////                        "각 25000원 입니다\n" +
////                        "직거래는 인성관 1층에서 진행합니다")
////                .setItemName("밀로 홀리데이 시그니처 볼캡")
////                .addImageFilename("39_j.jpg")
////                .addImageFilename("40_j.jpg")
////                .addImageFilename("41_j.jpg")
////                .setPrice(25000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.인성관)
////                .build();
////
////        //패션의류 5 나이키 쪼리 슬리퍼 판매합니다
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("20FW 이스트로그 셔츠 (XL) 판매")
////                .setContext("268000원에 구매하여 실착용 5회 미만으로 전체적으로 좋은 상태 유지중입니다.\n" +
////                        "표기상 사이즈 XL 이며 사이트에서 XL 는 품절입니다!\n" +
////                        "그만큼 많이들 찾으시는 사이즈 입니다\n" +
////                        "셔츠와 가디건 느낌을 동시에 느낄 수 있는 제품으로\n" +
////                        "허리 측면 스트링은 미국 M69 방탄조끼의 디테일을 모티브로하여\n" +
////                        "매력있는 디자인입니다\n" +
////                        "인성관 1층에서 직거래 원합니다 ")
////                .setItemName("이스트로그 셔츠")
////                .addImageFilename("42_j.jpg")
////                .addImageFilename("43_j.jpg")
////                .addImageFilename("44_j.jpg")
////                .setPrice(268000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.인성관)
////                .build();
////
////
////        //전자기기 1
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("아이폰12미니 레드색상 256기가 판매")
////                .setContext("아이폰12미니 레드색상 256기가 상태좋은단말기 39만에 판매합니다\n" +
////
////                        "선택약정할인25% 확정기변 가능 합니\n" +
////
////                        "3사 모든통신사,알뜰폰 사용 가능 합니다\n" +
////
////                        "배터리 효율 80% 이며 사설수리 이력 없습니다\n" +
////
////                        "액정화면 파손 기스 잔상 빛샘 불량화소없는 단말기 입니다\n" +
////                        "상상관 1층에서 거래 진행합니다!!")
////                .setItemName("아이폰 12 미니 레드 256G")
////                .addImageFilename("45_j.jpg")
////                .addImageFilename("46_j.jpg")
////                .addImageFilename("47_j.jpg")
////                .setPrice(390000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////
////
////        //전자기기 2
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("아이폰 11프로 256G 블랙 판매")
////                .setContext("아이폰 11프로 256G 블랙 액정무잔상 잔상 파손없는 최저가 38만원팝니다\n" +
////                        "선택약정할인25% , 확정기변 가능합니다\n" +
////                        "3사 모든통신사, 알뜰폰 사용 가능 합니다\n" +
////                        "배터리효율 75% 사설수리 침수이력없습니다" +
////                        "상상관 1층에서 거래 진행합니다!!")
////                .setItemName("아이폰 11프로 256G 블랙")
////                .addImageFilename("48_j.jpg")
////                .addImageFilename("49_j.jpg")
////                .addImageFilename("50_j.jpg")
////                .setPrice(390000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        //전자기기 3
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("갤럭시 S10E 화이트 128G 팝니다")
////                .setContext("전체적인 외관 A급 입니다\n" +
////                        "잔상 없으며 모든 기능 정상작동 합니다\n" +
////                        "유심끼워 사용 가능 합니다\n" +
////                        "최초 통신사 LG이나 모든 통신사 사용 가능 합니다\n" +
////                        "최초 통화일 2019년 11월 23일 입니다\n" +
////                        "상상관 1층에서 직거래 원합니다!")
////                .setItemName("갤럭시 S10E 화이트 128G")
////                .addImageFilename("51_j.jpg")
////                .addImageFilename("52_j.jpg")
////                .addImageFilename("53_j.jpg")
////                .setPrice(390000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////
////
////        //전자기기 4
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("아이패드9세대 64기가 스페이스그레이 팝니다")
////                .setContext("아이패드 9세대 스페이스그레이 64기가 와이파이\n" +
////                        "올해 1월 신품 구매하여 집에서만 사용했구요\n" +
////                        "사용빈도가 적어 판매합니다\n" +
////                        "구성품은 충전기 본체 박스 케이스 입니다\n" +
////                        "직거래는 상상관 1층에서 원합니다!")
////                .setItemName("아이패드9세대 64기가 스페이스그레이")
////                .addImageFilename("54_j.jpg")
////                .setPrice(250000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////
////        //전자기기 5
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("아이패드 8세대 32gb 택포 23에 팝니다")
////                .setContext("2년전에 사서 거의 인강만 들어서 깨끗합니다\n" +
////                        "필름 제가 붙여서 공기방울 들어간것이니 아무이상도 없고 찍힌자국 없어요\n" +
////                        "케이스에만 넣어두어서 먼지가 많아요\n" +
////                        "짭펜슬이랑 충전기랑 본체는 있는데 상자는 아무리 찾아도 없습니다ㅠ\n" +
////                        "직거래는 상상관 1층에서 원합니다!")
////                .setItemName("아이패드 8세대 32gb")
////                .addImageFilename("55_j.jpg")
////                .addImageFilename("56_j.jpg")
////                .setPrice(230000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////
////        //뷰티미용 1
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("에스까다 이스페셜리 오드퍼퓸 향수 50ml 팝니다")
////                .setContext("미개봉) 에스까다 이스페셜리 오드퍼퓸 50ml 향수 팝니다\n" +
////                        "향수 상태는 미개봉 입니다\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("에스까다 이스페셜리 오드퍼퓸 향수 50ml")
////                .addImageFilename("57_j.JPG")
////                .addImageFilename("58_j.JPG")
////                .addImageFilename("59_j.JPG")
////                .setPrice(50000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////
////        //뷰티미용 2
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("루이비통향수 아트라프레브 200미리 정품")
////                .setContext("미사용새상품!매장정품 200미리입니다~\n" +
////                        "100미리 가격으로 200미리 득템하세요\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("아트라프레브 향수 200ml")
////                .addImageFilename("70_j.jpg")
////                .addImageFilename("71_j.jpg")
////                .addImageFilename("72_j.jpg")
////                .setPrice(420000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //뷰티미용 3
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("(새제품)톰포드 향수 패뷸러스 50ML")
////                .setContext("톰포드 패뷸러스 50ML 2025.10.27\n" +
////                        "25만원\n" +
////                        "직거래나 구매 결정시 구매내역 확인,동봉해 드립니다\n" +
////                        "제품 구매 결정하시기 전에 충분히 구매 제품에 대한 가격비교 검색하시고\n" +
////                        "판매 상품이 맘에 드시면 연락 주시기 바랍니다.\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("톰포드 향수 패뷸러스 50ML")
////                .addImageFilename("60_j.jpg")
////                .setPrice(250000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //뷰티미용 4
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("베르사체 에로스 맨 EDT 100ML 팝니다")
////                .setContext("남성향수 1위 할정로도 베스트아이템으로 꼽히는 명품 브랜드 베르사체 에로스 향수 제품이에요.\n" +
////                        "제가 제일 좋아하는 향으로 완전 강추!! \n" +
////                        "유행타지않고 4계절 꾸준히 인기있는 남자향수 입니다.\n" +
////                        "중후하면서 멋스러운 기분좋은 향으로 베이스 노트가 끝장인 최고의 명품 향수입니다. \n" +
////                        "베르사체 특유의 고급스러운 디자인으로 제작된 향수병으로 선물용으로도 적극 추천해드립니다. \n" +
////                        "직거래는 공학관 1층 상상플러스에서 진행할게요!")
////                .setItemName("베르사체 에로스 맨 EDT 100ML ")
////                .addImageFilename("61_j.jpg")
////                .addImageFilename("62_j.jpg")
////                .setPrice(45000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //뷰티미용 5
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("휴고보스 소울 90ml")
////                .setContext("휴고보스 소울 edt 90ml\n" +
////                        "조금 사용했습니다.\n" +
////                        "향수양은 사진을 참고하세요.\n" +
////                        "뚜껑은 없습니다.\n" +
////                        "박스도 없어요.\n" +
////                        "46,000원\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("휴고보스 소울 90ml")
////                .addImageFilename("63_j.jpg")
////                .addImageFilename("64_j.jpg")
////                .setPrice(46000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////
////
////        //  부기굿즈 1
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("부기 우산 팝니다")
////                .setContext("부기 우산 팝니다\n" +
////                        "조금 사용했습니다.\n" +
////                        "너무 귀여운 부기 우산 가져가세요~~.\n" +
////                        "46,000원\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("부기우산")
////                .addImageFilename("65_j.jpeg")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //  부기굿즈 2
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("부기 토트백 팝니다")
////                .setContext("부기 토트백 팝니다\n" +
////                        "조금 사용했습니다.\n" +
////                        "너무 귀여운 부기 토트백 가져가세요~~.\n" +
////                        "30,000원\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("부기 토트백")
////                .addImageFilename("66_j.png")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.디지털콘텐츠가상현실트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //  부기굿즈 3
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("부기 토트백 흰,검 팝니다")
////                .setContext("부기 토트백 팝니다\n" +
////                        "조금 사용했습니다.\n" +
////                        "너무 귀여운 부기 토트백 흰,검 각각 1개 가져가세요~~.\n" +
////                        "30,000원\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("부기 토트백")
////                .addImageFilename("67_j.jpeg")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        //  부기굿즈 4
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("부기 인형 팝니다")
////                .setContext("부기 인형 팝니다\n" +
////                        "조금 사용했습니다.\n" +
////                        "너무 귀여운 부기 인형 가져가세요~~.\n" +
////                        "46,000원\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("부기인형")
////                .addImageFilename("68_j.jpeg")
////                .setPrice(15000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.빅데이터트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("스테들러 필기구 4종 멀티세트")
////                .setContext("스테들러 4종 멀티세트 입니다.\n" +
////                        "살 때 인터넷 최저가로 개당 5천원에 주고 샀어요~\n" +
////                        "구성품 확인해보시라고 인터넷상의 제품설명 사진에 첨부했어요~\n" +
////                        "6개 중 2개 파로 4개 남았습니다\n" +
////                        "개당 2500원에 팔고 4개 모두 한꺼번에 구매하시면 9000원에 드릴게요~\n" +
////                        "\n" +
////                        "부명초 앞 직거래 가능합니다\n" +
////                        "(사정상 평일 오후 8시 이후, 주말 아무 때나 가능)")
////                .setItemName("필기구")
////                .addImageFilename("minkyu20.jpeg")
////                .setPrice(2500)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("필기구 팝니당")
////                .setContext("이제 공부할일이 없어서 다 팝니다")
////                .setItemName("필기구")
////                .addImageFilename("minkyu21.jpeg")
////                .setPrice(5000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("필기구 일괄 판매합니다")
////                .setContext("궁금하신점은 문자주세요")
////                .setItemName("필기구")
////                .addImageFilename("minkyu22.jpeg")
////                .addImageFilename("minkyu23.jpeg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("몽블랑 필기구와 장식품")
////                .setContext("몽블랑에서 나온 소소한 학용품들입니다.\n" +
////                        "\n" +
////                        "지우개 + 자 + 연필 3 자루 + 인형 입니다.\n" +
////                        "인형은 등에 클래식 만년필을 지고 있는데 디테일도 있고 참 예쁩니다.\n" +
////                        "\n" +
////                        "수원시청역 직거래 가능합니다.")
////                .setItemName("필기구")
////                .addImageFilename("minkyu24.jpeg")
////                .setPrice(23000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("고시용 필기구의 최강자! 에너겔 메탈팁 45개 판매")
////                .setContext("고시용 필기구에 최강자 펜텔 에너겔 메탈팁\n" +
////                        "검정 39, 빨강 3, 파랑 3\n" +
////                        "총 45자루입니다\n" +
////                        "\n" +
////                        "펜 팁 그대로 붙어 있는 새것 입니다\n" +
////                        "\n" +
////                        "자루당 1,000원 입니다\n" +
////                        "\n" +
////                        "연락주세요^^")
////                .setItemName("필기구")
////                .addImageFilename("minkyu25.jpeg")
////                .addImageFilename("minkyu26.jpeg")
////                .setPrice(45000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("크로스 볼펜 필기구 마블시리즈 헐크 cross rolling ball pen marvel hulk")
////                .setContext("cross rolling ball pen\n" +
////                        "\n" +
////                        "크로스 볼펜입니다.\n" +
////                        "마블 헐크 모델입니다.\n" +
////                        "새것과 같은 상태입니다.\n" +
////                        "\n" +
////                        "궁금하신 점은 전화주세요.")
////                .setItemName("필기구")
////                .addImageFilename("minkyu27.jpeg")
////                .addImageFilename("minkyu28.jpeg")
////                .addImageFilename("minkyu29.jpeg")
////                .setPrice(80000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        // 생활가전 7개
////
////        // 부기 굿즈 7개
////
////        // 뷰티 미용 7개
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("메이크업자격증, 미용자격증, 미용재료")
////                .setContext("연습용으로 사용하기 좋아요\n" +
////                        "메이크업 자격증 할 때 사용했어요~")
////                .setItemName("미용 용품")
////                .addImageFilename("minkyu30.jpeg")
////                .setPrice(25000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("미용대 미용의자 미용실샴푸의자 팔아요")
////                .setContext("작년에 중고로 40만원에 구매했어요 ~\n" +
////                        "\n" +
////                        "어머님 다리가 안 좋으셔서 몇 번 사용했다가 회복하셔서 판매합니다^^\n" +
////                        "\n" +
////                        "수도꼭지는 빼버려서 철물점에서 냉온수 가능한 걸로 따로구매하셔야 할 것 같아 싸게 올려둡니다^^\n" +
////                        "\n" +
////                        "직접 가져가셔야 하고요 SUV 차량엔 실릴 것 같네요 ~\n" +
////                        "\n" +
////                        "곤지암 오향리 입니다")
////                .setItemName("미용 용품")
////                .addImageFilename("minkyu31.jpeg")
////                .addImageFilename("minkyu32.jpeg")
////                .addImageFilename("minkyu33.jpeg")
////                .setPrice(70000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("강아지미용가위 바리깡 미용 해먹거치대 나들이띠 합")
////                .setContext("강아지입양받아 미용시키려고발등 구매하여 2회사용하고 다른데로 분양보내면서 필요없어져 새제품이나 다름없어요 필요하신분 직거래로 연락바람니다 사진참고하시면됩니다")
////                .setItemName("미용 용품")
////                .addImageFilename("minkyu34.jpeg")
////                .addImageFilename("minkyu35.jpeg")
////                .addImageFilename("minkyu36.jpeg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("향수 일괄로 싸게 처분합니다 포맨트 코튼허그 비레디 보이블랙")
////                .setContext("포맨트 코튼허그 50ml\n" +
////                        "비레디 보이블랙 50ml\n" +
////                        "일괄 사용감 10회미만이고 상태최상 입니다\n" +
////                        "동생이 향수를 쓰지않아 싸게 처분합니다\n" +
////                        "일괄로만 판매합니다")
////                .setItemName("뷰티미용")
////                .addImageFilename("minkyu37.jpeg")
////                .setPrice(45000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("빅토르앤롤프 플라워밤 향수+봉봉 오드 향수")
////                .setContext("구매해서 10번도 안뿌린 것 같아요!\n" +
////                        "향 좋습니다!!!! 근데 향수정리하면서 향수가 많아서 이 친구도 보내주려 합니다...\n" +
////                        "플러워밤(본품) + 봉봉(미니) 다 드립니다! 박스도 있어요!\n" +
////                        "\n" +
////                        "신대방삼거리역 거래가능\n" +
////                        "문고리 거래가능\n" +
////                        "네고가능")
////                .setItemName("필기구")
////                .addImageFilename("minkyu38.jpeg")
////                .addImageFilename("minkyu39.jpeg")
////                .setPrice(30000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("크리드 향수공병 톰포드 향수공병")
////                .setContext("크리드 어벤투스 100ml 20,000\n" +
////                        "톰포드 네롤리 50ml 20,000\n" +
////                        "크리드 실버마운틴 75ml 20,000")
////                .setItemName("향수")
////                .addImageFilename("minkyu40.jpeg")
////                .setPrice(20000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("입생로랑 몽파리 오 드 뚜왈렛 루미에르 향수 30 미리 입생향수")
////                .setContext("선물받구 안써서 내놔요")
////                .setItemName("향수")
////                .addImageFilename("minkyu41.jpeg")
////                .addImageFilename("minkyu42.jpeg")
////                .addImageFilename("minkyu43.jpeg")
////                .setPrice(65000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.IT융합공학부)
////                .setTrackType(TrackType.지능시스템트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////
////
////        // brave
////        //  부기굿즈 5
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("부기 공책 팝니다")
////                .setContext("부기 공책 팝니다\n" +
////                        "조금 사용했습니다.\n" +
////                        "너무 귀여운 부기 공책 가져가세요~~.\n" +
////                        "10000원\n" +
////                        "직거래는 공학관 1층에서 원합니다!")
////                .setItemName("부기공책")
////                .addImageFilename("69_j.jpeg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("크록스 스타일의 슈즈")
////                .setContext("여름철 필수품. 사이즈265~270 정도입니다")
////                .setItemName("크록스슈즈")
////                .addImageFilename("brave_clothes_1_1.jpg")
////                .setPrice(9500)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("자라 흰색 오프숄더 s")
////                .setContext("1회 착용, 하자X 크롭기장이고 원가 49000입니다")
////                .setItemName("자라 흰색 오프숄더")
////                .addImageFilename("brave_clothes_2_1.jpg")
////                .addImageFilename("brave_clothes_2_2.jpg")
////                .setPrice(17000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////        new PostBuilder().setWhoPosted(brave)
////                .setTitle("보부상백, 크로스백")
////                .setContext("한번도 안맸어요! 바닥이 판판하게 돼있습니당")
////                .setItemName("크로스백")
////                .addImageFilename("brave_clothes_3_1.jpg")
////                .addImageFilename("brave_clothes_3_2.jpg")
////                .addImageFilename("brave_clothes_3_3.jpg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("머메이드 롱 스커트 아이보리")
////                .setContext("사이즈가 안 맞아서 보관만 했어요")
////                .setItemName("머메이드 롱스커트")
////                .addImageFilename("brave_clothes_4_1.jpg")
////                .addImageFilename("brave_clothes_4_2.jpg")
////                .addImageFilename("brave_clothes_4_3.jpg")
////                .setPrice(19000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("(새제품) 나이키 레거시91")
////                .setContext("선물 받았는데 저한테 좀 크네요..")
////                .setItemName("나이키 레거시91")
////                .addImageFilename("brave_clothes_5_1.jpg")
////                .addImageFilename("brave_clothes_5_2.jpg")
////                .setPrice(20000)
////                .setCategoryType(CategoryType.패션의류)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("이솝 카르스트")
////                .setContext("Aesop Karst 향수입니다. 시원한 물향이고 주말에만 거래 가능합니다.")
////                .setItemName("이솝카르스트")
////                .addImageFilename("brave_beauty_1_1.jpg")
////                .addImageFilename("brave_beauty_1_2.jpg")
////                .addImageFilename("brave_beauty_1_3.jpg")
////                .setPrice(120000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("스와로브스키 팔찌 팔아요")
////                .setContext("선물 받고 한 번 쓴건데 예쁘게 하실 분이 가져가세요~")
////                .setItemName("스와로브스키 팔찌")
////                .addImageFilename("brave_beauty_2_1.jpg")
////                .addImageFilename("brave_beauty_2_2.jpg")
////                .addImageFilename("brave_beauty_2_3.jpg")
////                .setPrice(45000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상관없음)
////                .build();
////        new PostBuilder().setWhoPosted(brave)
////                .setTitle("시계 파슬 자동시계 정품 팔아요")
////                .setContext("FOSSIL 정품. 시간 정확하고 상태 좋습니다.")
////                .setItemName("FOSSIL 자동시계")
////                .addImageFilename("brave_beauty_3_1.jpg")
////                .addImageFilename("brave_beauty_3_2.jpg")
////                .addImageFilename("brave_beauty_3_3.jpg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(brave)
////                .setTitle("바이레도 블랑쉬 향수 50ml")
////                .setContext("정품이고 선물 받았는데 취향 아니라 팔아요!!")
////                .setItemName("바이레도 블랑쉬")
////                .addImageFilename("brave_beauty_4_1.jpg")
////                .addImageFilename("brave_beauty_4_2.jpg")
////                .setPrice(110000)
////                .setCategoryType(CategoryType.뷰티미용)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("딥디크 도손 EDT 100ml")
////                        .setContext("2022 3월 한정판 에디션입니다. 거의 사용 안했어요")
////                        .setItemName("딥디크 도손")
////                        .addImageFilename("brave_beauty_5_1.jpg")
////                        .setPrice(100000)
////                        .setCategoryType(CategoryType.뷰티미용)
////                        .setDepartmentType(DepartmentType.컴퓨터공학부)
////                        .setTrackType(TrackType.모바일소프트웨어트랙)
////                        .setLocationType(LocationType.미래관)
////                        .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("아이폰 6S 32기가(32GB)")
////                .setContext("액정 깔끔하고, 외관도 위쪽 생활기스 말고는 없어요")
////                .setItemName("아이폰6S 32GB")
////                .addImageFilename("brave_digital_1_1.jpg")
////                .addImageFilename("brave_digital_1_2.jpg")
////                .addImageFilename("brave_digital_1_3.jpg")
////                .setPrice(130000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("에어팟 3세대 팔아요")
////                .setContext("4개월 정도 사용했고, 충전기 그대로 있어요")
////                .setItemName("에어팟 3세대")
////                .addImageFilename("brave_digital_2_1.jpg")
////                .setPrice(150000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////
////
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("Z플립4 크림 256G 자급제폰")
////                .setContext("핑크 골드, 유심 꽂고 바로 쓰시면 돼요 상태 S급")
////                .setItemName("Z플립4 256G")
////                .addImageFilename("brave_digital_3_1.jpg")
////                .addImageFilename("brave_digital_3_2.jpg")
////                .addImageFilename("brave_digital_3_3.jpg")
////                .addImageFilename("brave_digital_3_4.jpg")
////                .setPrice(770000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(brave)
////                .setTitle("브리츠 bt4000 무선 헤드셋")
////                .setContext("실사용기간 일주일도 안 돼요")
////                .setItemName("브리츠 bt4000")
////                .addImageFilename("brave_digital_4_1.jpg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.전자기기)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("컴퓨터 전체 팔아요")
////                .setContext("삼성 커브모니터 32인치 + 인텔i5 + 키보드 + 마우스 + 스피커")
////                .setItemName("컴퓨터 전체")
////                .addImageFilename("brave_digital_5_1.jpg")
////                .addImageFilename("brave_digital_5_2.jpg")
////                .addImageFilename("brave_digital_5_3.jpg")
////                .setPrice(416000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.낙산관)
////                .build();
////
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("두피 마사지기 팔아요")
////                .setContext("미용실에서 보고 구매했어요. 1회 사용.")
////                .setItemName("두피 마사지기")
////                .addImageFilename("brave_life_1_1.jpg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("제스파 아이피스 눈 마사지기")
////                .setContext("단순 개봉 미사용입니다.")
////                .setItemName("눈 마사지기")
////                .addImageFilename("brave_life_2_1.jpg")
////                .addImageFilename("brave_life_2_2.jpg")
////                .addImageFilename("brave_life_2_3.jpg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("레삐 마이오 옐로우 고데기")
////                .setContext("단발 통령으로 유명해요!! 구매 당시 10만원")
////                .setItemName("레삐 마이오 고데기")
////                .addImageFilename("brave_life_3_1.jpg")
////                .addImageFilename("brave_life_3_2.jpg")
////                .addImageFilename("brave_life_3_3.jpg")
////                .setPrice(60000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.낙산관)
////                .build();
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("블링어스 넥밴드 선풍기")
////                .setContext("블링어스 에어 클론 BLI-F1. 사용X 개봉만 해봤어요")
////                .setItemName("블링어스 넥밴드 선풍기")
////                .addImageFilename("brave_life_4_1.jpg")
////                .addImageFilename("brave_life_4_2.jpg")
////                .addImageFilename("brave_life_4_3.jpg")
////                .setPrice(19000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.풋살장)
////                .build();
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("코끼리 미니 핸디형 선풍기")
////                .setContext("컴팩트한 사이즈라 편하고 새상품 입니다!")
////                .setItemName("블링어스 넥밴드 선풍기")
////                .addImageFilename("brave_life_5_1.jpg")
////                .addImageFilename("brave_life_5_2.jpg")
////                .setPrice(19000)
////                .setCategoryType(CategoryType.생활가전)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.풋살장)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("세계사톡 1~5권 (새책)")
////                .setContext("비닐 포장 없지만 새책이에요~")
////                .setItemName("세계사톡 1~5권")
////                .addImageFilename("brave_book_1_1.jpg")
////                .addImageFilename("brave_book_1_2.jpg")
////                .addImageFilename("brave_book_1_3.jpg")
////                .setPrice(35000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("캠벨 생명과학 12판 새 책")
////                .setContext("샀는데 안 읽어서 팝니다. 정가 6만")
////                .setItemName("캠벨 생명과학")
////                .addImageFilename("brave_book_2_1.jpg")
////                .setPrice(50000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("실내건축기사 필기 2023")
////                .setContext("5페이지 외에는 아예 새책입니다")
////                .setItemName("실내건축기사2023")
////                .addImageFilename("brave_book_3_1.jpg")
////                .setPrice(25000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.창의관)
////                .build();
////        new PostBuilder().setWhoPosted(brave)
////                .setTitle("석고 연필데생 책 팔아용")
////                .setContext("상태 최상급입니다")
////                .setItemName("석고 연필데생 책")
////                .addImageFilename("brave_book_4_1.jpg")
////                .addImageFilename("brave_book_4_2.jpg")
////                .addImageFilename("brave_book_4_3.jpg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////        new PostBuilder().setWhoPosted(brave)
////                .setTitle("HTML5+CSS3+Javascript")
////                .setContext("사용감 있어서 싸게 팔아요")
////                .setItemName("HTML+CSS3")
////                .addImageFilename("brave_book_5_1.jpg")
////                .addImageFilename("brave_book_5_2.jpg")
////                .setPrice(11000)
////                .setCategoryType(CategoryType.도서)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(brave)
////                .setTitle("펜텔 그래프기어 1000 실버")
////                .setContext("거의 안 썼어요 샤프심도 드려요!")
////                .setItemName("펜텔 그래프기어")
////                .addImageFilename("brave_writing_1_1.jpg")
////                .setPrice(11000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("에버하드파버 헤라클릿 연필")
////                .setContext("2차 세계대전 시절 연필입니다. 분양해요")
////                .setItemName("에버하드파버 연필")
////                .addImageFilename("brave_writing_2_1.jpg")
////                .addImageFilename("brave_writing_2_2.jpg")
////                .addImageFilename("brave_writing_2_3.jpg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("목공예 통나무 연필통(난초)")
////                .setContext("새상품인데 포장지는 없어요")
////                .setItemName("목공예 통나무 연필통")
////                .addImageFilename("brave_writing_3_1.jpg")
////                .setPrice(10000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.인성관)
////                .build();
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("루이까또즈 가죽 팬케이스")
////                .setContext("사용감 있어서 저렴하게 팔아요")
////                .setItemName("루이까또즈 팬케이스")
////                .addImageFilename("brave_writing_4_1.jpg")
////                .addImageFilename("brave_writing_4_2.jpg")
////                .setPrice(11000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("사라사 제브라 필기구 세트")
////                .setContext("전부 새거입니다!")
////                .setItemName("필기구 세트")
////                .addImageFilename("brave_writing_5_1.jpg")
////                .setPrice(9000)
////                .setCategoryType(CategoryType.필기구)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.공학관)
////                .build();
////
////        new PostBuilder().setWhoPosted(jys)
////                .setTitle("상상부기 그립톡 팔아요")
////                .setContext("미사용 입니다!")
////                .setItemName("상상부기 그립톡")
////                .addImageFilename("brave_bugi_1_1.jpg")
////                .setPrice(3000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.미래관)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("상상부기 슬리퍼 팝니다")
////                .setContext("1번 신어봤어요 사이즈 270")
////                .setItemName("상상부기 슬리퍼")
////                .addImageFilename("brave_bugi_2_1.png")
////                .setPrice(7000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.웹공학트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////        new PostBuilder().setWhoPosted(minkyu)
////                .setTitle("상상부기 에코백")
////                .setContext("2번 써서 사용감 없어요!")
////                .setItemName("상상부기 에코백")
////                .addImageFilename("brave_bugi_3_1.jpg")
////                .setPrice(6000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상빌리지)
////                .build();
////        new PostBuilder().setWhoPosted(brave)
////                .setTitle("상상부기 L홀더")
////                .setContext("하늘색4개 흰색4개 한 번에 팔아요!")
////                .setItemName("상상부기 L홀더")
////                .addImageFilename("brave_bugi_4_1.jpg")
////                .addImageFilename("brave_bugi_4_2.jpg")
////                .setPrice(4000)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
////        new PostBuilder().setWhoPosted(gunhee)
////                .setTitle("상상부기 노트")
////                .setContext("50매이고 A, B 버전 각각 1개입니다!")
////                .setItemName("상상부기 노트")
////                .addImageFilename("brave_bugi_5_1.jpg")
////                .setPrice(4500)
////                .setCategoryType(CategoryType.부기굿즈)
////                .setDepartmentType(DepartmentType.컴퓨터공학부)
////                .setTrackType(TrackType.모바일소프트웨어트랙)
////                .setLocationType(LocationType.상상관)
////                .build();
//
//
//
//        // 각 카테고리별로 다양한 제목 옵션 저장
//        Map<CategoryType, List<String>> categoryTitleOptions = new HashMap<>();
//        categoryTitleOptions.put(CategoryType.패션의류, Arrays.asList(
//                "세련된 티셔츠",
//                "다채로운 바지",
//                "편안한 스웨터",
//                "스타일리시한 자켓",
//                "유니크한 원피스",
//                "캐주얼한 상의",
//                "스타일리시한 스카프",
//                "고급스러운 코트",
//                "모던한 청바지",
//                "편안한 맨투맨"
//        ));
//        categoryTitleOptions.put(CategoryType.부기굿즈, Arrays.asList(
//                "귀여운 부기인형",
//                "다채로운 소품",
//                "부기인형 봉제인형",
//                "부기인형 컬렉션",
//                "사랑스러운 아기용품",
//                "유니크한 부기인형",
//                "고급스러운 인형",
//                "흔치 않은 부기인형",
//                "매력적인 소품",
//                "귀여운 동물 모양 소품"
//        ));
//        categoryTitleOptions.put(CategoryType.도서, Arrays.asList(
//                "베스트셀러",
//                "다채로운 책",
//                "신작 도서",
//                "추천도서",
//                "마음을 움직이는 이야기",
//                "자기계발서",
//                "유용한 레시피북",
//                "흥미로운 잡지",
//                "평화로운 시집",
//                "인생을 바꿔주는 책"
//        ));
//        categoryTitleOptions.put(CategoryType.생활가전, Arrays.asList(
//                "편리한 가전제품",
//                "다채로운 생활가전",
//                "고품질 가전제품",
//                "스마트 가전제품",
//                "혁신적인 가전제품",
//                "유용한 주방가전",
//                "효율적인 세탁기",
//                "품질 좋은 냉장고",
//                "모던한 전자제품",
//                "스타일리시한 생활가전"
//        ));
//        categoryTitleOptions.put(CategoryType.뷰티미용, Arrays.asList(
//                "화려한 향수",
//                "다채로운 뷰티 제품",
//                "고품질 화장품",
//                "효과적인 스킨케어 제품",
//                "유용한 메이크업 제품",
//                "귀여운 화장 솜",
//                "편리한 뷰티 소품",
//                "화려한 네일 아트",
//                "피부를 촉촉하게 해주는 제품",
//                "스타일리시한 뷰티 아이템"
//        ));
//        categoryTitleOptions.put(CategoryType.필기구, Arrays.asList(
//                "고급스러운 연필",
//                "다채로운 필기구",
//                "고품질 볼펜",
//                "편안한 쓰기 도구",
//                "화려한 마커",
//                "귀여운 스티커",
//                "효과적인 책갈피",
//                "편리한 지우개",
//                "스타일리시한 문구용품",
//                "유니크한 필기 도구"
//        ));
//        categoryTitleOptions.put(CategoryType.전자기기, Arrays.asList(
//                "고품질 맥북",
//                "다채로운 전자기기",
//                "신형 노트북",
//                "편리한 스마트폰",
//                "혁신적인 태블릿",
//                "유용한 노트북 악세사리",
//                "고품질 이어폰",
//                "화려한 스마트워치",
//                "스타일리시한 헤드폰",
//                "편안한 이어팟"
//        ));
//
//
//
//
//
//
//
//
//
//
//        for (int i =0;i<10000;i++) {
//            Post dummyPost = new Post();
//            int randomIndex = random.nextInt(collegeTypes.length);
//
//            // 랜덤 CollegeType 설정
//            dummyPost.setCollege(collegeTypes[randomIndex]);
//            String salePhrase = salePhrases[new Random().nextInt(salePhrases.length)];
//
//
//            // 랜덤 카테고리 설정
//            Category randomCategory = new Category();
//            randomCategory.setCategory_type(categories[random.nextInt(categories.length)]);
//            categoryJpaRepository.save(randomCategory);
//
//            //dummyPost.setCategory(randomCategory);
//            dummyPost.setCategoryType(categories[random.nextInt(categories.length)]);
//
//
//            List<String> titleOptions = categoryTitleOptions.get(dummyPost.getCategoryType());
//            String randomTitle = titleOptions.get(random.nextInt(titleOptions.size()));
//
//            dummyPost.setPost_title(randomTitle +" " +salePhrase);
//            dummyPost.setPost_text("게시글 내용");
//
//
//            dummyPost.setWho_posted(dummyMemberForSale);
//            dummyPost.setPrice(10000);
//
//            Department dummyDepartment = new Department();
//            dummyDepartment.setDepartmentType(departmentTypes[random.nextInt(departmentTypes.length)]);
//            departMentJpaRepository.save(dummyDepartment);
//            //dummyPost.setDepartment(dummyDepartment);
//            dummyPost.setDepartmentType(departmentTypes[random.nextInt(departmentTypes.length)]);
//
//            dummyPost.setLocationType(locations[random.nextInt(locations.length)]);
//            dummyPost.setLocation_text("101호");
//            dummyPost.generateRandomCreatedDate();
//
//            dummyPost.setItem_name("MacBook Pro 13");
//
//            if (i % 999 == 0) {
//                Purchased dummyPurchased = new Purchased();
//                dummyPurchased.setMember(brave);
////                dummyPurchased.setPrice(dummyPost.getPrice());
//                dummyPurchased.setPrice(i);
//                dummyPurchased.setPostTitle(dummyPost.getPost_title());
//                dummyPurchased.setItem_name("부기굿즈");
//                dummyPurchased.setQuantity(1);
//                dummyPurchased.setTid(UUID.randomUUID().toString());
//                dummyPurchased.setBuyer_username(gunhee.getUsername());
//                purchasedRepository.save(dummyPurchased);
//                dummyPost.setPurchased(dummyPurchased);
//                dummyPost.setStatus(StatusType.거래완료);
//            }
//            else{
//                dummyPost.setStatus(StatusType.판매중);
//            }
//
//
//            postRepository.savePost(dummyPost);
//            ArrayList<String> image_list = new ArrayList<>();
//
//
//            if (dummyPost.getCategoryType() == CategoryType.패션의류) {
//                Image dummyImage0 = new Image();
//                Image dummyImage1 = new Image();
//                Image dummyImage2 = new Image();
//                int randomNumber = random.nextInt(3) + 1;
//                dummyImage0.setImageFilename("clothes" + randomNumber + "_0.jpg");
//                dummyImage1.setImageFilename("clothes" + randomNumber + "_1.jpg");
//                dummyImage2.setImageFilename("clothes" + randomNumber + "_2.jpg");
//                image_list.add("clothes" + randomNumber + "_0.jpg");
//                image_list.add("clothes" + randomNumber + "_1.jpg");
//                image_list.add("clothes" + randomNumber + "_2.jpg");
//
//                dummyImage0.setPost(dummyPost);
//                dummyImage1.setPost(dummyPost);
//                dummyImage2.setPost(dummyPost);
//                imageRepository.save(dummyImage0);
//                imageRepository.save(dummyImage1);
//                imageRepository.save(dummyImage2);
//            } else if (dummyPost.getCategoryType() == CategoryType.부기굿즈) {
//                Image dummyImage = new Image();
//                int randomNumber = random.nextInt(3) + 1;
//
//                dummyImage.setImageFilename("boggie" + randomNumber + ".png");
//                image_list.add("boggie" + randomNumber + ".png");
//                dummyImage.setPost(dummyPost);
//                imageRepository.save(dummyImage);
//            } else if (dummyPost.getCategoryType() == CategoryType.전자기기) {
//                Image dummyImage0 = new Image();
//                Image dummyImage1 = new Image();
//                Image dummyImage2 = new Image();
//                int randomNumber = random.nextInt(3) + 1;
//                dummyImage0.setImageFilename("elec" + randomNumber + "_0.jpeg");
//                dummyImage1.setImageFilename("elec" + randomNumber + "_1.jpeg");
//                dummyImage2.setImageFilename("elec" + randomNumber + "_2.jpeg");
//
//                image_list.add("elec" + randomNumber + "_0.jpeg");
//                image_list.add("elec" + randomNumber + "_1.jpeg");
//                image_list.add("elec" + randomNumber + "_2.jpeg");
//
//                dummyImage0.setPost(dummyPost);
//                dummyImage1.setPost(dummyPost);
//                dummyImage2.setPost(dummyPost);
//                imageRepository.save(dummyImage0);
//                imageRepository.save(dummyImage1);
//                imageRepository.save(dummyImage2);
//            } else if (dummyPost.getCategoryType() == CategoryType.뷰티미용) {
//
//                Image dummyImage0 = new Image();
//                Image dummyImage1 = new Image();
//                Image dummyImage2 = new Image();
//                int randomNumber = random.nextInt(3) + 1;
//                dummyImage0.setImageFilename("beauty" + randomNumber + "_0.webp");
//                dummyImage1.setImageFilename("beauty" + randomNumber + "_1.webp");
//                dummyImage2.setImageFilename("beauty" + randomNumber + "_2.webp");
//
//                image_list.add("beauty" + randomNumber + "_0.webp");
//                image_list.add("beauty" + randomNumber + "_1.webp");
//                image_list.add("beauty" + randomNumber + "_2.webp");
//
//                dummyImage0.setPost(dummyPost);
//                dummyImage1.setPost(dummyPost);
//                dummyImage2.setPost(dummyPost);
//                imageRepository.save(dummyImage0);
//                imageRepository.save(dummyImage1);
//                imageRepository.save(dummyImage2);
//            } else if (dummyPost.getCategoryType() == CategoryType.도서) {
//                Image dummyImage = new Image();
//                int randomNumber = random.nextInt(5) + 1;
//                dummyImage.setImageFilename("book" + randomNumber + ".jpeg");
//
//                image_list.add("book" + randomNumber + ".jpeg");
//
//
//                dummyImage.setPost(dummyPost);
//                imageRepository.save(dummyImage);
//            } else if (dummyPost.getCategoryType() == CategoryType.필기구) {
//                Image dummyImage = new Image();
//                dummyImage.setImageFilename("writing_implement1.png");
//                dummyImage.setPost(dummyPost);
//                image_list.add("writing_implement1.png");
//
//                imageRepository.save(dummyImage);
//            } else if (dummyPost.getCategoryType() == CategoryType.생활가전) {
//                Image dummyImage = new Image();
//                int randomNumber = random.nextInt(5) + 1;
//                dummyImage.setImageFilename("appliances" + randomNumber + ".jpeg");
//                image_list.add("appliances" + randomNumber + ".jpeg");
//
//                dummyImage.setPost(dummyPost);
//                imageRepository.save(dummyImage);
//            }
//
//
//
//
//            PostDocument pd = new PostDocument(dummyPost);
//
//            if(dummyPost.getPurchased() != null){
//                pd.setPurchase_id(dummyPost.getPurchased().getId());
//            }
//
//
//            for(String str :image_list){
//                pd.getImages().add(str);
//            }
//
//            postDocumentRepository.save(pd);
//
//        }
//
//
//    }
//
//
//
//
//
//    @Data
//    static class ChatRoomResponse {
//        private String sender;
//        private String receiver;
//
//        public ChatRoomResponse(String memberA, String memberB) {
//            this.sender = memberA;
//            this.receiver = memberB;
//        }
//    }
//
//    @Data
//    static class ChaRoomResponse {
//        private String usernameA;
//        private String usernameB;
//        private String message;
//
//        public ChaRoomResponse(Post post, ChatMessage chatMessage) {
//            this.usernameA = post.getWho_posted().getUsername();
//            this.usernameB = chatMessage.getMember().getUsername();
//            this.message = chatMessage.getMessage();
//        }
//    }
//    public class PostBuilder {
//        private Member whoPosted;
//        private String title;
//        private String context;
//        private String itemName;
//        private ArrayList<String> imageFilenames = new ArrayList<>();
//        private int price;
//        private CategoryType categoryType;
//        private DepartmentType departmentType;
//        private LocationType locationType;
//
//        private TrackType trackType;
//        private LocalDateTime createdDate;
//
//
//
//        public PostBuilder setWhoPosted(Member whoPosted) {
//            this.whoPosted = whoPosted;
//            return this;
//        }
//
//        public PostBuilder setTrackType(TrackType trackType) {
//            this.trackType = trackType;
//            return this;
//        }
//
//        public PostBuilder setTitle(String title) {
//            this.title = title;
//            return this;
//        }
//
//        public PostBuilder setContext(String context) {
//            this.context = context;
//            return this;
//        }
//
//        public PostBuilder setItemName(String itemName) {
//            this.itemName = itemName;
//            return this;
//        }
//
//        public PostBuilder addImageFilename(String imageFilename) {
//            this.imageFilenames.add(imageFilename);
//            return this;
//        }
//
//        public PostBuilder setPrice(int price) {
//            this.price = price;
//            return this;
//        }
//        public void generateRandomCreatedDate() {
//            // Generate random month within the range of 1 (January) to 12 (December)
//            int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);
//
//            // Generate random day within the range of 1 to 28 (assumes a non-leap year)
//            int randomDay = ThreadLocalRandom.current().nextInt(1, 29);
//            this.createdDate = LocalDateTime.of(2023, randomMonth, randomDay, 0, 0);
//        }
//        public PostBuilder setCategoryType(CategoryType categoryType) {
//            this.categoryType = categoryType;
//            return this;
//        }
//
//        public PostBuilder setDepartmentType(DepartmentType departmentType) {
//            this.departmentType = departmentType;
//            return this;
//        }
//
//        public PostBuilder setLocationType(LocationType locationType) {
//            this.locationType = locationType;
//            return this;
//        }
//
//        public void build() {
//            Post hardcodePost = new Post();
//            hardcodePost.setPost_title(title);
//            hardcodePost.setPost_text(context);
//            hardcodePost.setWho_posted(whoPosted);
//            hardcodePost.setItem_name(itemName);
//            hardcodePost.setPrice(price);
//            hardcodePost.setTrack(trackType);
//
//            Category category = new Category();
//            category.setCategory_type(categoryType);
//            categoryJpaRepository.save(category);
//            //hardcodePost.setCategory(category);
//
//            Department department = new Department(departmentType);
//            departMentJpaRepository.save(department);
//            //hardcodePost.setDepartment(department);
//            hardcodePost.setLocationType(locationType);
//
//            postRepository.savePost(hardcodePost);
//
//            for(String imageFilename : imageFilenames) {
//                Image imageHard = new Image();
//                imageHard.setImageFilename(imageFilename);
//                imageHard.setPost(hardcodePost);
//                imageRepository.save(imageHard);
//            }
//        }
//    }
//}
//
