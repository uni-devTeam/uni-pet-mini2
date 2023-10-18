package com.example.unipet.mypage.controller;

import com.example.unipet.mypage.dao.MyBoardMapper;
import com.example.unipet.mypage.dao.MyMapper;
import com.example.unipet.mypage.domain.BoardVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"userId", "myname"})
public class MyBoardController {

    @Autowired
    MyBoardMapper dao;

    @Autowired
    MyMapper mydao;

    @RequestMapping(value = "/myshare")
    public String myshare(@ModelAttribute("userId") String userId, Model model) {
        model.addAttribute("share", dao.getMyShare(userId));
        model.addAttribute("myname", mydao.getMyName(userId));
        return "mypage/myshare";
    }

    @RequestMapping(value = "/mysharelikes")
    public String mysharelikes(@ModelAttribute("userId") String userId, Model model) {
        model.addAttribute("likes", dao.getShareLikes(userId));
        model.addAttribute("myname", mydao.getMyName(userId));
        return "mypage/mysharelikes";
    }
}
