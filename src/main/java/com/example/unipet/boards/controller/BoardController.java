package com.example.unipet.boards.controller;

import com.example.unipet.boards.dao.BoardMapper;
import com.example.unipet.boards.domain.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("board")
public class BoardController {
    @Autowired
    BoardMapper dao;

    @RequestMapping("posting")
    public String writePost() {
        return "posting";
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

    @RequestMapping("sblist")
    public ModelAndView shareBoardList() {
        List<BoardDTO> list = dao.list();
        ModelAndView mav = new ModelAndView();
        if (list.size() != 0) {
            mav.addObject("list", list);
        } else {
            mav.addObject("msg", "작성된 게시물이 존재하지 않습니다.");
        }
        mav.setViewName("board");
        return mav;
    }

    @RequestMapping("sbsave")
    public ModelAndView insert(BoardDTO save) {
        boolean result = dao.insert(save);
        ModelAndView mav = new ModelAndView();
        if (result) {
            mav.setViewName("redirect:/board/sblist");
        } else {
            mav.addObject("msg", "글 등록에 실패했습니다.");
        }
        return mav;
    }

    @RequestMapping("sbdelete")
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

    @RequestMapping(value = "/content", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView showContent(@RequestParam("board_no") int board_no) {
        System.out.println(board_no);

        dao.increaseViews(board_no);
        BoardDTO board = dao.showContent(board_no);
        ModelAndView mav = new ModelAndView();
        mav.addObject("board", board);
        mav.setViewName("content");
        return mav;
    }

    @RequestMapping(value = "/parsejson", produces = "application/json; charset=utf-8")
    @ResponseBody
    public BoardDTO one(int board_no) {
        return dao.one(board_no);
    }
}
