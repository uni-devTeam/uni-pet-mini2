package com.example.unipet.mypage.dao;

import com.example.unipet.mypage.domain.UserResultDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MyMapper {

    // 메인
    @Select("SELECT name FROM user WHERE user_id = #{sessionId}")
    public String getMyName(@Param("sessionId") String sessionId);

    // 회원 정보
    @Select("SELECT user_id, password, email, name FROM user WHERE user_id = #{sessionId}")
    public UserResultDTO getMyInfo(@Param("sessionId") String sessionId);

    // 이메일 수정
    @Update("UPDATE user " +
            "SET email = #{email} " +
            "WHERE user_id = #{sessionId}")
    public boolean changeEmail(@Param("sessionId") String sessionId, @Param("email") String email);

    // 비밀번호 확인
    @Select("SELECT password FROM user WHERE user_id = #{sessionId}")
    public String getPass(@Param("sessionId") String sessionId);

    // 비밀번호 수정
    @Update("UPDATE user SET password = #{password} WHERE user_id = #{sessionId}")
    public void changeMyPass(@Param("sessionId") String sessionId, @Param("password") String password);

    // 회원탈퇴 -> cascade 쓸건지...
    @Delete("DELETE FROM user WHERE user_id = #{sessionId}")
    public void deleteAccount(@Param("sessionId") String sessionId);
}
