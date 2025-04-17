package capstone.market.controller;

import capstone.market.domain.PurchaseReview;
import capstone.market.service.PurchaseReviewService;
import capstone.market.transaction_dto.PurchaseReviewDTO;
import capstone.market.transaction_dto.Seller_ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PurchaseReviewController {

    private final PurchaseReviewService purchaseReviewService;

    @PostMapping("/add_purchase_review")
    public void PurchaseReview(@RequestBody PurchaseReviewDTO purchaseReviewDTO){
        purchaseReviewService.savePurchaseReview(purchaseReviewDTO);

    }


    @GetMapping("/get_seller_profile")
    public Seller_ProfileDTO getSellerProfile(@RequestParam Long userId){

        return purchaseReviewService.getSellerProfile(userId);
    }


    @GetMapping("/get_all_purchase_review")
        public List<PurchaseReviewDTO> getAllPurchaseReview(@RequestParam Long userId){

            return purchaseReviewService.getAllPurchaseReview(userId);

        }


}
