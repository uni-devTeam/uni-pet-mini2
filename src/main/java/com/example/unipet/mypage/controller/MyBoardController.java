package com.example.unipet.mypage.controller;

import com.example.unipet.mypage.dao.MyBoardMapper;
import com.example.unipet.mypage.dao.MyMapper;
import com.example.unipet.mypage.domain.BoardVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyBoardController {

    @Autowired
    MyBoardMapper dao;

    @Autowired
    MyMapper mydao;

    @RequestMapping(value = "/myshare")
    public String myshare(@SessionAttribute(name = "userId", required = false) String sessionId, Model model) {
        model.addAttribute("share", dao.getMyShare(sessionId));
        model.addAttribute("myname", mydao.getMyName(sessionId));
        return "mypage/myshare";
    }

    @RequestMapping(value = "/mysharelikes")
    public String mysharelikes(@SessionAttribute(name = "userId", required = false) String sessionId, Model model) {
        model.addAttribute("likes", dao.getShareLikes(sessionId));
        model.addAttribute("myname", mydao.getMyName(sessionId));
        return "mypage/mysharelikes";
    }
}
