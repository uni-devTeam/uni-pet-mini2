package com.example.unipet.mypage.controller;

import com.example.unipet.mypage.dao.MyBoardMapper;
import com.example.unipet.mypage.dao.MyMapper;
import com.example.unipet.mypage.domain.BoardVO;
import com.example.unipet.mypage.domain.MyWritingVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/getMywriting")
    @ResponseBody
    public List<MyWritingVO> getMywriting(@ModelAttribute("userId") String userId) {
        return dao.getMyWritings(userId);
    }

    // 나의 게시글 페이지
    @RequestMapping(value = "/mywriting")
    public String mywriting() {
        return "mypage/mywriting";
    }

    @RequestMapping("/items")
    @ResponseBody
    public List<MyWritingVO> paging(@RequestParam("limit") int limit,
                                        @RequestParam("page") int page,
                                    @ModelAttribute("userId") String userId) {
        System.out.println(limit);
        System.out.println(page);
        int offset = (page - 1) * limit;
        return dao.getPagingItems(limit, offset, userId);
    }

}
