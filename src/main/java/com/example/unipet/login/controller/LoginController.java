package com.example.unipet.login.controller;

import com.example.unipet.login.dao.LoginMapper;
import com.example.unipet.login.model.LoginDTO;
import com.example.unipet.signup.dao.SignupMapper;
import com.example.unipet.signup.model.SignupDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    LoginMapper dao;

    @GetMapping("/login")
    public String signupMain() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginReq(LoginDTO dto, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView();

        String id = dto.getId();
        String passWord = dto.getPassword();

        if (dao.checkId(id)) {
            if (dao.checkPassword(id, passWord)) {
                System.out.println("로그인 성공");

                httpServletRequest.getSession().invalidate();
                HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성

                session.setAttribute("userId", id);
                session.setMaxInactiveInterval(1800);

                mav.setViewName("");
            } else {
                System.out.println("비밀번호가 틀립니다.");
                mav.addObject("message", "비밀번호가 일치하지 않습니다.");
                mav.setViewName("login");
            }
        } else {
            System.out.println("없는 아이디 입니다.");
            mav.addObject("message", "존재하지 않는 ID 입니다.");
            mav.setViewName("login");
        }
        return mav;
    }

}
