package com.example.unipet.boards.controller;

import com.example.unipet.boards.dao.BoardMapper;
import com.example.unipet.boards.domain.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("board")
public class ShareBoardController {
    @Autowired
    BoardMapper dao;

    @RequestMapping("sblist")
    public ModelAndView shareBoardList() {
        List<BoardDTO> list = dao.list();
        ModelAndView mav = new ModelAndView();
        if (list.size() != 0) {
            mav.addObject("list", list);
        } else {
            mav.addObject("msg", "작성된 게시물이 존재하지 않습니다.");
        }
        mav.setViewName("shareBoardView");
        return mav;
    }

}
