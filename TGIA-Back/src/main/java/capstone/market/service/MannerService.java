package capstone.market.service;

import capstone.market.domain.Manner;
import capstone.market.domain.Member;
import capstone.market.manner_dto.MannerCheckListDTO;
import capstone.market.repository.MannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MannerService {
    private final MemberService memberService;
    private final MannerRepository mannerRepository;
    public void setMannerScore(MannerCheckListDTO mannerData) {
        Long sellerId = mannerData.getSellerId();
        Member member = memberService.findMemberByPK(sellerId);
        Integer checkedCnt = 0;
        Manner manner = member.getManner();
        Long cnt = 0L;
        manner.setTotalFeed(manner.getTotalFeed() + 1);
        if (mannerData.getReDealing()) {
            manner.setReDealing(manner.getReDealing() + 1);
        }
        if (mannerData.getGoodPrice()) {
            cnt = manner.getGoodPrice() + 1;
            checkedCnt += 1;
            manner.setGoodPrice(cnt);
        }

        if (mannerData.getGoodTime()) {
            cnt = manner.getGoodTime() + 1;
            checkedCnt += 1;
            manner.setGoodTime(cnt);
        }

        if (mannerData.getFastResponse()) {
            cnt = manner.getFastResponse() + 1;
            checkedCnt += 1;
            manner.setFastResponse(cnt);
        }

        if (mannerData.getNoResponse()) {
            cnt = manner.getNoResponse() + 1;
            checkedCnt -= 1;
            manner.setNoResponse(cnt);
        }

        if (mannerData.getBadQuality()) {
            cnt = manner.getBadQuality() + 1;
            checkedCnt -= 1;
            manner.setBadQuality(cnt);
        }
        System.out.println("fadfasfds " + checkedCnt);
        Integer totalMannerScore = checkedCnt * 5;
        member.setMannerscore(member.getMannerscore() + mannerData.getMannerScore() + totalMannerScore);
        mannerRepository.save(manner);
        member.setManner(manner);
    }
    public List<Map<String, Long>> topThreeAndReDealingRate(Long userId) {
        Member member = memberService.findMemberByPK(userId);
        Manner manner = member.getManner();

        // 재거래 희망률 추가
        double percentage = (double) manner.getReDealing() / manner.getTotalFeed() * 100;
        Long roundedPercentage = Math.round(percentage);

        // Assuming the coefficients are stored in a map with labels
        Map<String, Long> coefficientMap = new HashMap<>();
        coefficientMap.put("좋은 상품을 저렴하게 판매해요", manner.getGoodPrice());
        coefficientMap.put("시간 약속을 잘 지켜요", manner.getGoodTime());
        coefficientMap.put("응답 속도가 빨라요", manner.getFastResponse());
        coefficientMap.put("상품에 하자가 있어요", manner.getBadQuality());
        coefficientMap.put("연락이 잘 안 돼요", manner.getNoResponse());

        // Sort the coefficients in descending order
        List<Map.Entry<String, Long>> sortedCoefficients = new ArrayList<>(coefficientMap.entrySet());
        Collections.sort(sortedCoefficients, (e1, e2) -> Long.compare(e2.getValue(), e1.getValue()));

        // Get the top 3 coefficients with labels
        List<Map.Entry<String, Long>> top3Coefficients = sortedCoefficients.subList(0, 3);

        // Output the top 3 coefficients with labels
        for (Map.Entry<String, Long> entry : top3Coefficients) {
            String label = entry.getKey();
            Long coefficient = entry.getValue();
            System.out.println(label + ": " + coefficient);
        }

        List<Map<String, Long>> mapList = new ArrayList<>();

        for (Map.Entry<String, Long> entry : top3Coefficients) {
            Map<String, Long> map = new HashMap<>();
            map.put(entry.getKey(), entry.getValue());
            mapList.add(map);
        }

        Map<String, Long> rate = new HashMap<>();
        rate.put("reDealingRate", roundedPercentage);
        mapList.add(rate);

        return mapList;
    }
}
