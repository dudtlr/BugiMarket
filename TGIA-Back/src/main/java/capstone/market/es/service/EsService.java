package capstone.market.es.service;

import capstone.market.es.dto.PostDocumentDTO;

import capstone.market.es.repository.PostDocumentElasticsearchRepository;
import capstone.market.profile_dto.SearchFilterDto2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor

public class EsService {

    private final PostDocumentElasticsearchRepository esRepository;

    public List<PostDocumentDTO> search(SearchFilterDto2 searchFilterDto) throws IOException {


        return esRepository.search(searchFilterDto);
    }


}