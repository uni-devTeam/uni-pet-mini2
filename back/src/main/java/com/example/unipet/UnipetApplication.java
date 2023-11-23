package com.example.unipet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
<<<<<<< HEAD:src/main/java/com/example/unipet/UnipetApplication.java
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
=======
import org.springframework.scheduling.annotation.EnableScheduling;
>>>>>>> 3c344a7e01ca80af0c63a53693be497f5ce03373:back/src/main/java/com/example/unipet/UnipetApplication.java


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.unipet"})
@MapperScan(value = {"com.example.unipet.mypage.dao", "com.example.unipet.signup.dao",
<<<<<<< HEAD:src/main/java/com/example/unipet/UnipetApplication.java
        "com.example.unipet.login.dao", "com.example.unipet.main.dao",
        "com.example.unipet.boards.dao", "com.example.unipet.list.dao"})
@EnableJpaRepositories(basePackages = {"springjpa.exam.repository"})
@EntityScan(basePackages = {"springjpa.exam.entity"})
=======
         "com.example.unipet.main.dao",
        "com.example.unipet.boards.dao", "com.example.unipet.list.converter"})
@EnableScheduling
>>>>>>> 3c344a7e01ca80af0c63a53693be497f5ce03373:back/src/main/java/com/example/unipet/UnipetApplication.java
public class UnipetApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnipetApplication.class, args);
    }

}
