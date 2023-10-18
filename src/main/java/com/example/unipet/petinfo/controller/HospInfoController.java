package com.example.unipet.petinfo.controller;

import com.example.unipet.mypage.dao.MyMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HospInfoController {

    @GetMapping(value = "/hospinfo")
    public String getHospinfo() {
        return "hospInfo";
    }
}
