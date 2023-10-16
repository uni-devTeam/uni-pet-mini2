package com.example.unipet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.unipet", "thymeleaf.exam"})
@MapperScan(value={"com.example.unipet.mypage.dao"})
public class UnipetApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnipetApplication.class, args);
    }

}
