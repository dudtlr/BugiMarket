package capstone.market.service;

import capstone.market.domain.Favorite;
import capstone.market.repository.FavoriteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteJpaRepository favoriteJpaRepository;

    // 좋아요 저장

    @Transactional
    public Favorite save(Favorite favorite) {
        return favoriteJpaRepository.save(favorite);
    }


    //좋아요 검색
    public Favorite findById(Long favoriteId) {
        return favoriteJpaRepository.findById(favoriteId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 좋아요가 존재하지 않습니다."));
    }

    // 좋아요 삭제
    @Transactional
    public void delete(Long favoriteId) {
        Favorite favorite = favoriteJpaRepository.findById(favoriteId).get();
        favoriteJpaRepository.delete(favorite);
    }




    //좋아요 전체 리스트
    public List<Favorite> findAll(){
        return favoriteJpaRepository.findAll();
    }



}
