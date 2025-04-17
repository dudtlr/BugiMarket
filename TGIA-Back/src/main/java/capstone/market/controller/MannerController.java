package capstone.market.controller;

import capstone.market.manner_dto.MannerCheckListDTO;
import capstone.market.service.MannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MannerController {
    private final MannerService mannerService;
    @GetMapping("/manner")
    public void manner() {
        System.out.println("testing");
    }

    @PostMapping("/manner/set_score")
    public void getMannerPoints(@RequestBody MannerCheckListDTO request) {
        mannerService.setMannerScore(request);
    }

    @GetMapping("/manner/getTop3AndDealingRate")
    public List<Map<String, Long>> getTop3(Long userId) {
        return mannerService.topThreeAndReDealingRate(userId);
    }
}
