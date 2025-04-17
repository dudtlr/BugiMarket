package capstone.market.es.repository;

import capstone.market.es.domain.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
public interface PostDocumentRepository extends ElasticsearchRepository<PostDocument, Long> {

}