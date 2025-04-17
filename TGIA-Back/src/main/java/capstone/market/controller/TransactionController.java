package capstone.market.controller;

import capstone.market.domain.Member;
import capstone.market.profile_dto.ProfileListDto;
import capstone.market.service.TransactionService;
import capstone.market.transaction_dto.AdminStatisticsDTO;
import capstone.market.transaction_dto.PurchasedDTO;
import capstone.market.transaction_dto.PurchasedWithPostTitleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;


    @GetMapping("/transaction")
    public AdminStatisticsDTO getTransactionCounts(){
        return transactionService.getTransactionCounts();
    }

    @GetMapping("/transaction2")
    public Map<String, Map<String, Long>> getMonthlyTransactionCountsByCategory(){
        return transactionService.getMonthlyPostCountsByCategory(2023);
    }


    @GetMapping("/userlist")
    public List<ProfileListDto> getUserList(){
        return transactionService.getUserList();
    }

    @GetMapping("/purchasedlist")
    public List<PurchasedDTO> getPurchasedList(){
        return transactionService.getPurchasedList();
    }

    @GetMapping("/purchasedlistV2")
    public List<PurchasedWithPostTitleDTO> getPurchasedListV2(){
        return transactionService.getPurchasedListV2();
    }
}


