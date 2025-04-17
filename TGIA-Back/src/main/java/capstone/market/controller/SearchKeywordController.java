package capstone.market.controller;


import capstone.market.domain.Post;
import capstone.market.domain.SearchKeyword;
import capstone.market.searchKeyword_dto.SearchKeywordDTO;
import capstone.market.service.PostService;
import capstone.market.service.SearchKeywordService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SearchKeywordController {


  private final SearchKeywordService searchKeywordService;


  @GetMapping("/getTop10keywords")
    public List<String> findTop10ByOrderBySearchCountDesc(){

      ArrayList<String> objects = new ArrayList<>();
      List<SearchKeywordDTO> top10ByOrderBySearchCountDesc = searchKeywordService.findTop10ByOrderBySearchCountDesc();
      for (SearchKeywordDTO searchKeywordDTO : top10ByOrderBySearchCountDesc) {

          objects.add(searchKeywordDTO.getKeyword());

      }

      return objects;
    }





}
