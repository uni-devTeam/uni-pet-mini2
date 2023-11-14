package com.example.unipet.mypage.service;

import com.example.unipet.mypage.domain.BoardDTO;
import com.example.unipet.mypage.domain.MyPagingDTO;
import com.example.unipet.mypage.entity.Boards;
import com.example.unipet.mypage.repository.MyBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardsService {
    private final MyBoardRepository myBoardRepository;

    private static final Map<Integer, Integer> PAGE_SIZES = Map.of(
            1, 6,  // 나눔게시판의 페이지 크기는 6
            2, 10  // 자유게시판의 페이지 크기는 10
    );

    public List<BoardDTO> getMyWritings(MyPagingDTO myPagingDTO) {
        int pageSize = PAGE_SIZES.getOrDefault(myPagingDTO.getBoardId(), 0);

        if(pageSize == 0) {
            return Collections.emptyList();
        }

        Pageable pageable = PageRequest.of(myPagingDTO.getPage(), pageSize,
                                            Sort.by(Sort.Order.desc("boardNo")));
        List<Boards> result = myBoardRepository.findByUserIdAndBoardId(myPagingDTO.getUserId(),
                                                                myPagingDTO.getBoardId(), pageable);
        // 가비지 컬렉터가 나머지 찾지 않는 값 가져감(메모리 누수) 수정필
        List<BoardDTO> list = result.stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
        return result.isEmpty() ? Collections.emptyList() : list;
    }
}
