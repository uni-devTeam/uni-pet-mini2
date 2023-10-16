package com.example.unipet.mypage.controller;

import com.example.unipet.mypage.dao.MyMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MyController {

    @Autowired
    MyMapper dao;

    // 메인
    @RequestMapping(value = "/mypage")
    public String mypage(Model model, HttpSession session) {
        session.setAttribute("sessionId", "user");
        String sessionId = (String) session.getAttribute("sessionId");
        model.addAttribute("myname", dao.getMyName(sessionId));
        return "mypage/main";
    }

    // 회원 정보
    @RequestMapping(value = "/myprofile")
    public String myprofile(Model model, HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");
        model.addAttribute("me", dao.getMyInfo(sessionId));
        return "mypage/profile";
    }

    // 로그아웃
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/main";
    }

    // 이메일 변경 페이지
    @RequestMapping(value = "/myemail")
    public String myemail(Model model, HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");
        model.addAttribute("me", dao.getMyInfo(sessionId));
        return "mypage/myemail";
    }

    // 이메일 변경
    @RequestMapping(value = "/changeemail")
    public String emailchange(Model model, HttpSession session, @RequestParam("email") String customEmail, @RequestParam("domain") String domain) {
        String sessionId = (String) session.getAttribute("sessionId");
        String combinedEmail = customEmail + "@" + domain; // 이메일을 조합
        dao.changeEmail(sessionId, combinedEmail);
        return "redirect:/myprofile";
    }

    // 비밀번호 확인 페이지
    @RequestMapping(value = "/mypasswd")
    public String passcheckPage(Model model, HttpSession session, @RequestParam(value = "action", required = false) String action) {
        String sessionId = (String) session.getAttribute("sessionId");
        System.out.println(action);
        model.addAttribute("action", action);
        return "mypage/mypasscheck";
    }

    // 비밀번호 확인
    @RequestMapping(value = "/passcheck")
    public String passcheck(Model model, HttpSession session,
                            @RequestParam("currentPass") String currentPass,
                            @RequestParam(value = "action", required = false) String action) {
        String sessionId = (String) session.getAttribute("sessionId");
        System.out.println(action);

        if(currentPass.equals(dao.getPass(sessionId))) {
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
    public String passchange(Model model, HttpSession session,
                             @RequestParam("changedPass") String changedPass,
                             @RequestParam("changedPassCheck") String changedPassCheck) {
        String sessionId = (String) session.getAttribute("sessionId");
        if(changedPass.equals(changedPassCheck)) {
            dao.changeMyPass(sessionId, changedPassCheck);
            System.out.println("비밀번호 변경 완료");
            return "redirect:/myprofile";
        }
        return "mypage/myerror";
    }

    // 회원 탈퇴
    @RequestMapping(value = "/mydelaccount")
    public String deleteAcc(Model model, HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");
        dao.deleteAccount(sessionId);
        return "mypage/withdraw";
    }
}
