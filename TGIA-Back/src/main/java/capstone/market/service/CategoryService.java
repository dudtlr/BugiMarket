package capstone.market.service;

import capstone.market.domain.*;
import capstone.market.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;


    //1트랙 2트랙 레포지토리에 저장
    //public void
    @Transactional
    public void UpdateCategory(Category category,CategoryType categoryType) {
        category.setCategory_type(categoryType);
        categoryJpaRepository.save(category);
    }





//     수정하기
//    @Transactional
//    public FirstTrack update(Long trackId, BoardEditDto boardEditDto) {
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 존재하지 않습니다."));
//        return board.change(boardEditDto);
//    }

    //




}
