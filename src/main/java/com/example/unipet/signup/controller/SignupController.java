package com.example.unipet.signup.controller;

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
public class SignupController {
    @Autowired
    SignupMapper dao;

    @GetMapping("/signup")
    public String signupMain() {
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView signupReq(SignupDTO dto) {
        ModelAndView mav = new ModelAndView();
        if(!validationDto(dto, mav)){
            mav.setViewName("signup");
            return mav;
        }
        mav.setViewName("signup");
        return mav;
    }

    public boolean validationDto(SignupDTO dto, ModelAndView mav) {
        if (dto.getEmail().isEmpty())  {
            mav.addObject("message", "아이디를 입력해주세요.");
            return false;
        }
        if (dto.getPassword().isEmpty()) {
            mav.addObject("message", "패스워드를 입력해주세요.");
            return false;
        }
        if (dto.getName().isEmpty()) {
            mav.addObject("message", "이름을 입력해주세요.");
            return false;
        }
        if (dto.getHavePet() == null) {
            mav.addObject("message", "펫 보유 여부를 입력해주세요.");
            return false;
        }
        if (dto.getPetName().isEmpty()) {
            mav.addObject("message", "펫 이름을 입력해주세요.");
            return false;
        }
        if (dto.getPetBirthday().isEmpty()) {
            mav.addObject("message", "펫 생일을 입력해주세요.");
            return false;
        }
        if (dto.getPetGender().isEmpty()) {
            mav.addObject("message", "펫 성별을 입력해주세요.");
            return false;
        }
        if (dto.getPetType().isEmpty()) {
            mav.addObject("message", "펫 종류을 입력해주세요.");
            return false;
        }
        if (dto.getPetPicture().isEmpty()) {
            mav.addObject("message", "펫 사진을 입력해주세요.");
            return false;
        }
        if (dto.getDoNeutering() == null) {
            mav.addObject("message", "펫 중성화여부를 입력해주세요.");
            return false;
        }
        if (dto.getPetColor().isEmpty()) {
            mav.addObject("message", "펫 색상을 입력해주세요.");
            return false;
        }
        if (dto.getPetChar().isEmpty()) {
            mav.addObject("message", "펫 특징을 입력해주세요.");
            return false;
        }
        return true;
    }
}
