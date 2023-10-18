package com.example.unipet.mypage.dao;

import com.example.unipet.mypage.domain.UserResultDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MyMapper {

    // 메인
    @Select("SELECT name FROM user WHERE user_id = #{userId}")
    public String getMyName(@Param("userId") String userId);

    // 회원 정보
    @Select("SELECT user_id, password, email, name FROM user WHERE user_id = #{userId}")
    public UserResultDTO getMyInfo(@Param("userId") String userId);

    // 이메일 수정
    @Update("UPDATE user " +
            "SET email = #{email} " +
            "WHERE user_id = #{userId}")
    public boolean changeEmail(@Param("userId") String userId, @Param("email") String email);

    // 비밀번호 확인
    @Select("SELECT password FROM user WHERE user_id = #{userId}")
    public String getPass(@Param("userId") String userId);

    // 비밀번호 수정
    @Update("UPDATE user SET password = #{password} WHERE user_id = #{userId}")
    public void changeMyPass(@Param("userId") String userId, @Param("password") String password);

    // 회원탈퇴 -> cascade 쓸건지...
    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    public void deleteAccount(@Param("userId") String userId);
}
