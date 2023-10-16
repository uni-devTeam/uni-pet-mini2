package com.example.unipet.mypage.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserVO {
    private String user_id;
    private String password;
    private String email;
    private String name;
}
