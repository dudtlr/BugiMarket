package capstone.market.es.controller;


import capstone.market.es.dto.PostDocumentDTO;
import capstone.market.es.service.EsService;
import capstone.market.profile_dto.SearchFilterDto2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EsController {

    private final EsService esService;


    @GetMapping("/es_search")
    public List<PostDocumentDTO> search( SearchFilterDto2 searchFilterDto) throws IOException {

        List<PostDocumentDTO> postDocuments = esService.search(searchFilterDto);

        return postDocuments;
    }


}