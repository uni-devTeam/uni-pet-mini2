package com.example.unipet.mypageTest;

import com.example.unipet.mypage.entity.User;
import com.example.unipet.mypage.repository.MyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MyTest {
    @Autowired private MyRepository myRepository;

    @BeforeEach
    void pr() { System.out.println("=".repeat(80)); }

    // 메인
    @Test
    void getMyName() {
        System.out.println("유저이름: " + myRepository.findNameByUserId("user"));
    }

    // 회원 정보
    @Test
    void getMyInfo() {
        System.out.println("회원 정보: " + myRepository.findUserByUserId("user"));
    }

    // 이메일 수정
    @Test
    void changeEmail() {
        User user = myRepository.findUserByUserId("user");
        System.out.println("수정 전 이메일: " + user.getEmail());
        user.setEmail("newEmail@unipet.com");

        User changedUser = myRepository.findUserByUserId("user");
        System.out.println("수정 후 이메일: " + changedUser.getEmail());
    }

    // 비밀번호 확인
    @Test
    void findPass() {
        System.out.println("비밀번호: " + myRepository.findPasswordByUserId("user"));
    }

    // 비밀번호 수정
    @Test
    void changeMyPass() {
        User user = myRepository.findUserByUserId("user");
        System.out.println("수정 전 비밀번호: " + user.getPassword());
        user.setPassword("1111");

        User changedUser = myRepository.findUserByUserId("user");
        System.out.println("수정 후 비밀번호: " + changedUser.getPassword());
    }

    // 회원탈퇴
    @Test
    void setStatusOut() {
        User user = myRepository.findUserByUserId("user");
        System.out.println("수정 전 status: " + user.getRoles());
        user.setRoles("out");

        User changedUser = myRepository.findUserByUserId("user");
        System.out.println("수정 후 status: " + changedUser.getRoles());
    }

}
