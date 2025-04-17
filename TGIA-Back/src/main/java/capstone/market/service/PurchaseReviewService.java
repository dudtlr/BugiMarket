package capstone.market.service;

import capstone.market.domain.Image;
import capstone.market.domain.Member;
import capstone.market.domain.Post;
import capstone.market.domain.PurchaseReview;
import capstone.market.profile_dto.ProfileListDto;
import capstone.market.repository.*;
import capstone.market.transaction_dto.PurchaseReviewDTO;
import capstone.market.transaction_dto.Seller_ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseReviewService {
    private final PurchaseReviewJpaRepository purchaseReviewJpaRepository;

    private final MemberRepository memberRepository;

    private final PurchaseReviewRepository purchaseReviewRepository;

    private final PostRepository postRepository;



    public void savePurchaseReview(PurchaseReviewDTO purchaseReviewDTO){

        PurchaseReview purchaseReview = new PurchaseReview();
        Long buyerId = purchaseReviewDTO.getBuyer_id();
        Long sellerId = purchaseReviewDTO.getSeller_id();
        Member seller = memberRepository.findOne(sellerId);
        Member buyer = memberRepository.findOne(buyerId);
        String imageFilename = buyer.getImage().getImageFilename();
        String review = purchaseReviewDTO.getReview();

        Post one = postRepository.findOne(purchaseReviewDTO.getPost_id());
        one.setReviewType("후기완료");
        purchaseReview.setSeller_id(sellerId);
        purchaseReview.setBuyer_id(buyerId);
        purchaseReview.setBuyer_username(buyer.getUsername());
        purchaseReview.setUser_type("구매자");
        purchaseReview.setReview(review);
        purchaseReview.setImageFilename(imageFilename);

        postRepository.savePost(one);
        purchaseReviewJpaRepository.save(purchaseReview);

    }

    public Seller_ProfileDTO getSellerProfile(Long userId){

        Member one = memberRepository.findOne(userId);
        ProfileListDto profileListDto = new ProfileListDto(one);
        List<PurchaseReviewDTO> latestPurchaseReviews = purchaseReviewRepository.findLatestPurchaseReviews(userId);

        Seller_ProfileDTO sellerProfileDTO = new Seller_ProfileDTO(profileListDto, latestPurchaseReviews);
        sellerProfileDTO.setPurchaseReview_전체개수(purchaseReviewRepository.getPurchaseReviewCount(userId));

        sellerProfileDTO.setCreatedDate(one.getCreatedDate());

        sellerProfileDTO.setCountSellPostbyUser(postRepository.findSellListCount(userId));

        
        return sellerProfileDTO;

    }

    public List<PurchaseReviewDTO> getAllPurchaseReview(Long userId){

        return purchaseReviewRepository.getAllPurchaseReview(userId);

    }



}
