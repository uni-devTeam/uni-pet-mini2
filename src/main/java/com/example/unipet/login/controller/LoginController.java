package com.example.unipet.login.controller;

import com.example.unipet.signup.dao.SignupMapper;
import com.example.unipet.signup.model.SignupDTO;
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
    SignupMapper dao;

    @GetMapping("/login")
    public String signupMain() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView signupReq(SignupDTO dto) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("login");
        return mav;
    }

}
