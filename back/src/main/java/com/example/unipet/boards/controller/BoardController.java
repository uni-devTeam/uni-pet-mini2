package com.example.unipet.boards.controller;

import com.example.unipet.boards.repository.BoardRepository;
import com.example.unipet.boards.repository.CommentRepository;
import com.example.unipet.boards.domain.CommentDTO;
import com.example.unipet.boards.entity.Board;
import com.example.unipet.boards.entity.Comment;
import com.example.unipet.boards.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;

    //작성된 게시물 리스트 가져오기
    @GetMapping("/list/{boardNo}")
    //응답 테스트 완료
    public Board getBoards(@PathVariable int boardNo) {
        return boardRepository.findByBoardNo(boardNo);
    }

    //게시글 작성하기
    @PostMapping("/save")
    //응답 테스트 완료
    public Board saveBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    //게시글 삭제하기
    @DeleteMapping("/delete/{boardNo}")
    //응답 테스트 완료
    public void deleteBoard(@PathVariable int boardNo) {
        boardRepository.deleteByBoardNo(boardNo);
    }

    //게시글 수정하기
    @PutMapping("/update")
    public Board updateBoard(@RequestBody Board board) {
        Board existingBoard = boardRepository.findByBoardNo(board.getBoardNo());
        if (existingBoard == null) {
            throw new IllegalArgumentException("Board not found with boardNo " + board.getBoardNo());
        }

        // 수정이 필요한 필드만 업데이트
        existingBoard.setBoardId(board.getBoardId());
        existingBoard.setTitle(board.getTitle());
        existingBoard.setContent(board.getContent());
        // 업데이트가 필요한 경우 다른 필드들도 이런식으로 수정해주면 됨

        // 수정된 existingBoard를 저장
        return boardRepository.save(existingBoard);
    }


    //게시글 제목으로 검색하기
    @GetMapping("/search/{title}")
    public List<Board> searchBoards(@PathVariable String title) {
        return boardRepository.findByTitleContaining(title);
    }

    //해당 게시글 내용 가져오기
    @GetMapping("/content/{boardNo}")
    //응답 테스트 완료
    public Board getBoard(@PathVariable int boardNo) {
        Board board = boardRepository.findByBoardNo(boardNo);
        if (board == null) {
            throw new IllegalArgumentException("Board not found with boardNo " + boardNo);
        } else {
            return board;
        }
    }

    // ======= 댓글 관련 메서드 선언 =======

    //댓글 생성
    @PostMapping("/comment")
    //응답 테스트 완료
    public CommentDTO saveComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setUserId(commentDTO.getUserId());
        comment.setContent(commentDTO.getContent());

        Board board = boardRepository.findByBoardNo(commentDTO.getBoardNo());
        if (board == null) {
            throw new IllegalArgumentException("Board not found with id " + commentDTO.getBoardNo());
        }

        // Comment가 참조할 Board 설정
        comment.setBoard(board);

        Comment savedComment = commentRepository.save(comment);

        // savedComment를 CommentDTO 객체로 변환하여 반환
        CommentDTO savedCommentDTO = new CommentDTO();
        savedCommentDTO.setCommentId(savedComment.getCommentId());
        savedCommentDTO.setUserId(savedComment.getUserId());
        savedCommentDTO.setContent(savedComment.getContent());
        savedCommentDTO.setBoardNo(savedComment.getBoard().getBoardNo());

        // commentDate 설정
        savedCommentDTO.setCommentDate(savedComment.getCommentDate());

        return savedCommentDTO;
    }

    //댓글 삭제
    @DeleteMapping("/comment/delete/{commentId}")
    //응답 테스트 완료
    public void deleteComment(@PathVariable int commentId) {
        commentRepository.deleteById(commentId);
    }

    //댓글 조회
    @GetMapping("/comments/{boardNo}")
    public List<Comment> getComments(@PathVariable int boardNo) {
        return commentRepository.findByBoard_BoardNo(boardNo);
    }

    //댓글 수정
    @PutMapping("/comment/update")
    //응답 테스트 완료
    public Comment updateComment(@RequestBody CommentDTO commentDTO) {
        //기존의 데이터를 가져와서 일부 필드만 수정한 후 다시 저장하는 방식
        Comment existingComment = commentRepository.findById(commentDTO.getCommentId()).orElseThrow(() -> new IllegalArgumentException("Comment not found with id " + commentDTO.getCommentId()));

        // 수정이 필요한 필드만 업데이트
        existingComment.setContent(commentDTO.getContent());
        return commentRepository.save(existingComment);
    }
}
