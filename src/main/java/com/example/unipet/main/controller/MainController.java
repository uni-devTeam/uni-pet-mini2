package com.example.unipet.main.controller;

import com.example.unipet.main.dao.MainMapper;
import com.example.unipet.signup.dao.SignupMapper;
import com.example.unipet.signup.model.SignupDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    MainMapper dao;

    @GetMapping("/")
    public ModelAndView goMain(@SessionAttribute(name = "userId", required = false) String userId) {
        ModelAndView mav = new ModelAndView();

        if(userId == null){
            System.out.println("널");
        }else{
            String name = dao.getName(userId);
            mav.addObject("name", name);
        }

        mav.setViewName("main");
        return mav;
    }

}
