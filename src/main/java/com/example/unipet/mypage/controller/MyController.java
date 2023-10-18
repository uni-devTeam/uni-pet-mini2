package com.example.unipet.mypage.controller;

import com.example.unipet.mypage.dao.MyMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"userId", "myname"})
public class MyController {

    @Autowired
    MyMapper dao;

    // 메인
    @RequestMapping(value = "/mypage")
    public String mypage(Model model, HttpSession session, @ModelAttribute("userId") String userId) {
        System.out.println(userId);
        String myname = dao.getMyName(userId);
        System.out.println(myname);
        session.setAttribute("myname", myname);
        model.addAttribute("myname", myname);
        return "mypage/main";
    }

    // 회원 정보
    @RequestMapping(value = "/myprofile")
    public String myprofile(Model model, @ModelAttribute("userId") String userId) {
        System.out.println(userId);
        model.addAttribute("me", dao.getMyInfo(userId));
        return "mypage/profile";
    }

    // 로그아웃
    @RequestMapping(value = "/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "/main";
    }

    // 이메일 변경 페이지
    @RequestMapping(value = "/myemail")
    public String myemail(Model model, @ModelAttribute("userId") String userId) {
        model.addAttribute("me", dao.getMyInfo(userId));
        return "mypage/myemail";
    }

    // 이메일 변경
    @RequestMapping(value = "/changeemail")
    public String emailchange(Model model, @ModelAttribute("userId") String userId, @RequestParam("email") String customEmail, @RequestParam("domain") String domain) {
        String combinedEmail = customEmail + "@" + domain; // 이메일을 조합
        dao.changeEmail(userId, combinedEmail);
        return "redirect:/myprofile";
    }

    // 비밀번호 확인 페이지
    @RequestMapping(value = "/mypasswd")
    public String passcheckPage(Model model, @ModelAttribute("userId") String userId, @RequestParam(value = "action", required = false) String action) {
        System.out.println(action);
        model.addAttribute("action", action);
        return "mypage/mypasscheck";
    }

    // 비밀번호 확인
    @RequestMapping(value = "/passcheck")
    public String passcheck(Model model, @ModelAttribute("userId") String userId,
                            @RequestParam("currentPass") String currentPass,
                            @RequestParam(value = "action", required = false) String action) {
        System.out.println(action);

        if(currentPass.equals(dao.getPass(userId))) {
            System.out.println("비밀번호 일치");
            if ("changepwd".equals(action)) {
                return "mypage/mypassChange";
            } else {
                return "redirect:/mydelaccount";
            }
        }

        return "mypage/myerror";
    }

    // 비밀번호 변경
    @RequestMapping(value = "/passchange")
    public String passchange(Model model, @ModelAttribute("userId") String userId,
                             @RequestParam("changedPass") String changedPass,
                             @RequestParam("changedPassCheck") String changedPassCheck) {
        if(changedPass.equals(changedPassCheck)) {
            dao.changeMyPass(userId, changedPassCheck);
            System.out.println("비밀번호 변경 완료");
            return "redirect:/myprofile";
        }
        return "mypage/myerror";
    }

    // 회원 탈퇴
    @RequestMapping(value = "/mydelaccount")
    public String deleteAcc(Model model, @ModelAttribute("userId") String userId) {
        dao.deleteAccount(userId);
        return "mypage/withdraw";
    }
}
