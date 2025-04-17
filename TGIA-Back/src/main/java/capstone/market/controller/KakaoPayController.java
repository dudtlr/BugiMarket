package capstone.market.controller;

import capstone.market.kakao_dto.KakaoApproveResponse;
import capstone.market.kakao_dto.KakaoCancelResponse;
import capstone.market.kakao_dto.KakaoPayDto;
import capstone.market.kakao_dto.KakaoReadyResponse;
import capstone.market.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public KakaoReadyResponse readyToKakaoPay(@RequestBody KakaoPayDto kakaoPayDto) {

        return kakaoPayService.kakaoPayReady(kakaoPayDto);
    }

    /**
     * 결제 성공
     */
    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {


        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);
        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }


    /**
     * 환불
     */
    @PostMapping("/refund")
    public ResponseEntity refund() {

        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();

        return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
    }
//    /**
//     * 결제 진행 중 취소  결제하기 버튼 누를 수 있는 곳으로 리다이렉션 시키자
//     */
//    @GetMapping("/cancel")
//    public void cancel() {
//
//        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
//    }
//
//    /**
//     * 결제 실패     결제하기 버튼 누를 수 있는 곳으로 리다이렉션 시키자
//     */
//    @GetMapping("/fail")
//    public void fail() {
//
//        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
//    }
}