package com.example.unipet.boards.controller;

import com.example.unipet.boards.dao.BoardMapper;
import com.example.unipet.boards.domain.BoardDTO;
import com.example.unipet.boards.domain.CommentDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("board")
public class BoardController {
    @Autowired
    BoardMapper dao;

    @RequestMapping("list{board_id}")
    public ModelAndView BoardList(@PathVariable("board_id") int board_id) {
        BoardDTO dto = new BoardDTO();
        dto.setBoard_id(board_id);

        List<BoardDTO> list = dao.boardId(dto);
        ModelAndView mav = new ModelAndView();
        mav.addObject("boardId", board_id);

        if (list.size() != 0) {
            mav.addObject("list", list);
//            String userId = (String) request.getSession().getAttribute("userId");
//            boolean showWriteButton = "admin".equals(userId);
//            mav.addObject("showWriteButton", showWriteButton);
        } else {
            mav.addObject("msg", "작성된 게시물이 존재하지 않습니다.");
        }
        mav.setViewName("board");
        return mav;
    }


    @RequestMapping("save")
    public ModelAndView insert(BoardDTO save) {
        boolean result = dao.insert(save);
        ModelAndView mav = new ModelAndView();
        if (result) {
            mav.setViewName("redirect:/board/list" + save.getBoard_id());
        } else {
            mav.addObject("msg", "글 등록에 실패했습니다.");
        }
        return mav;
    }

    @RequestMapping("delete")
    public ModelAndView delete(int board_no) {
        boolean result = dao.delete(board_no);
        ModelAndView mav = new ModelAndView();
        if (result) {
            mav.addObject("list", dao.list());
        } else {
            mav.addObject("msg", "글 삭제에 실패했습니다.");
        }
        mav.setViewName("board");
        return mav;
    }

    @RequestMapping(value = "/sbupdate",
            method = RequestMethod.POST)
    public ModelAndView update(BoardDTO dto) {
        boolean result = dao.update(dto);
        ModelAndView mav = new ModelAndView();
        if (result) {
            mav.addObject("board", dto);
        } else {
            mav.addObject("msg", "글 수정에 실패했습니다.");
        }
        mav.setViewName("content");
        return mav;
    }

    @RequestMapping("/sbsearch")
    public ModelAndView search(String title) {
        List<BoardDTO> list = dao.search(title);
        //System.out.println(list.size());
        ModelAndView mav = new ModelAndView();
        if (list.size() != 0) {
            mav.addObject("list", list);
        } else {
            mav.addObject("msg", "작성된 게시물이 존재하지 않습니다.");
        }
        mav.setViewName("board");
        return mav;
    }

    @RequestMapping("posting")
    public ModelAndView writePost(@RequestParam("board_id") int boardId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("boardId", boardId);
        mav.setViewName("posting");
        return mav;
    }

    @RequestMapping(value = "/content", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView showContent(@RequestParam("board_no") int board_no, HttpSession session) {
        System.out.println(board_no);
        dao.increaseViews(board_no);
        BoardDTO board = dao.showContent(board_no); //DTO 반환

        List<CommentDTO> comments = dao.getComments(board_no); //Comment 리스트 가져오기

        ModelAndView mav = new ModelAndView();
        mav.addObject("board", board);
        mav.addObject("boardId", board.getBoard_id());

        mav.addObject("comments", comments);

        String currentUserId = (String) session.getAttribute("userId");
        System.out.println("로그인 중인 아이디:" + currentUserId);
        if(currentUserId != null && currentUserId.equals((board.getUser_id()))) {
            mav.addObject("canEdit", true);
        } else {
            mav.addObject("canEdit", false);
        }

        mav.setViewName("content");
        return mav;
    }

    @RequestMapping(value = "/parsejson", produces = "application/json; charset=utf-8")
    @ResponseBody
    public BoardDTO one(int board_no) {
        return dao.one(board_no);
    }



}
