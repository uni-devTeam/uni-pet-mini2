package com.example.unipet.login.controller;


import com.example.unipet.login.config.auth.MyUserDetails;
import com.example.unipet.login.entity.LoginUser;
import com.example.unipet.login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginRepository loginRepository;

    @PostMapping("login")
    public String login() {
        return "토큰 발행 완료";
    }

//    @GetMapping("user")
//    public String user(@AuthenticationPrincipal MyUserDetails userDetails) {
//        System.out.println(userDetails.toString());
//        return "<h1>user</h1>";
//    }
//
//    @GetMapping("manager/reports")
//    public String reports() {
//        return "<h1>reports</h1>";
//    }
//
////	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @GetMapping("admin/users")
//    public List<LoginUser> users(){
//        return loginRepository.findAll();
//    }



}
