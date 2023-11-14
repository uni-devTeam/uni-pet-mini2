package com.example.unipet.mypage.controller;

import com.example.unipet.mypage.domain.BoardDTO;
import com.example.unipet.mypage.domain.MyPagingDTO;
import com.example.unipet.mypage.service.BoardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mypage")
@RequiredArgsConstructor
@SessionAttributes({"userId", "myname"})
public class MyBoardController {

    private final BoardsService boardsService;

    @GetMapping("/mywritings")
    public ResponseEntity<Map<String, Object>> mywritings(@RequestParam("userId") String userId,
                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam("boardId") int boardId) {
        MyPagingDTO myPagingDTO = MyPagingDTO.builder()
                .userId(userId)
                .page(page)
                .boardId(boardId)
                .build();
        List<BoardDTO> list = boardsService.getMyWritings(myPagingDTO);
        Map<String, Object> response = new HashMap<>();

        if(!list.isEmpty()) {
            response.put("list", list);
            return ResponseEntity.ok()
                    .body(response);
        } else {
            response.put("noWritings", "작성된 글이 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }

}
