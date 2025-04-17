package capstone.market.service;

import capstone.market.domain.SearchKeyword;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.repository.SearchKeywordRepository;
import capstone.market.searchKeyword_dto.SearchKeywordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchKeywordService {


    private final SearchKeywordRepository searchKeywordRepository;

    public List<SearchKeywordDTO> findTop10ByOrderBySearchCountDesc(){

        List<SearchKeyword> top10ByOrderBySearchCountDesc = searchKeywordRepository.findTop10ByOrderBySearchCountDesc();

        List<SearchKeywordDTO> SearchKeywords = top10ByOrderBySearchCountDesc.stream().map(s -> new SearchKeywordDTO(s))
                .collect(Collectors.toList());
        return SearchKeywords;
       
    };

}
