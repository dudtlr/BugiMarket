package capstone.market.service;

import capstone.market.domain.FirstTrack;
import capstone.market.domain.SecondTrack;
import capstone.market.domain.TrackType;
import capstone.market.repository.FirstTrackJpaRepository;
import capstone.market.repository.SecondTrackJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrackService {
    private final FirstTrackJpaRepository firstTrackJpaRepository;
    private final SecondTrackJpaRepository secondTrackJpaRepository;

    //1트랙 2트랙 레포지토리에 저장
    //public void
    @Transactional
    public void saveFirstTrack(FirstTrack firstTrack, TrackType trackType) {
        firstTrack.setFirst_track(trackType);
        firstTrackJpaRepository.save(firstTrack);
    }
    @Transactional
    public void saveSecondTrack(SecondTrack secondTrack, TrackType trackType) {
        secondTrack.setSecond_track(trackType);
        secondTrackJpaRepository.save(secondTrack);

    }

    //1트랙 2트랙 조회
    public FirstTrack findFirstTrackByid(Long trackId){
        return  firstTrackJpaRepository.findById(trackId).get();
    }

    public SecondTrack findSecondTrackByid(Long trackId){
        return secondTrackJpaRepository.findById(trackId).get();
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
