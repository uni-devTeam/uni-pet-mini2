package com.example.unipet.signup.repository;

import com.example.unipet.login.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface MypetRepository extends JpaRepository<User, String> {

    boolean existsByUserId(String userId);
    boolean existsByUserIdAndPassword(String userId, String password);
    String findRolesByUserId(String userId);

}