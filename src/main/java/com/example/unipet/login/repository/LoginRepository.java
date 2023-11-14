package com.example.unipet.login.repository;

import com.example.unipet.login.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface LoginRepository extends JpaRepository<User, String> {

    //    @Select("SELECT COUNT(*) > 0 FROM uni_pet.User WHERE user_id = #{id}")
//    public boolean checkId(String id);
    boolean existsByUserId(String userId);

    //    @Select("SELECT COUNT(*) > 0 AS user_exists FROM uni_pet.User WHERE user_id = #{id} AND password = #{password}")
//    public boolean checkPassword(@Param("id") String id, @Param("password") String password );
    boolean existsByUserIdAndPassword(String userId, String password);

    //    @Select("SELECT roles FROM uni_pet.User WHERE user_id = #{id}")
//    public String checkRoles(String id);
    String findRolesByUserId(String userId);

}