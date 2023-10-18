package com.example.unipet.mypage.dao;

import com.example.unipet.mypage.domain.UserResultDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MyMapper {

    // 메인
    @Select("SELECT name FROM user WHERE user_id = #{userId}")
    public String getMyName(String userId);

    // 회원 정보
    @Select("SELECT user_id, password, email, name FROM user WHERE user_id = #{userId}")
    public UserResultDTO getMyInfo(String userId);

    // 이메일 수정
    @Update("UPDATE user " +
            "SET email = #{email} " +
            "WHERE user_id = #{userId}")
    public boolean changeEmail(String userId, @Param("email") String email);

    // 비밀번호 확인
    @Select("SELECT password FROM user WHERE user_id = #{userId}")
    public String getPass(String userId);

    // 비밀번호 수정
    @Update("UPDATE user SET password = #{password} WHERE user_id = #{userId}")
    public void changeMyPass(String userId, @Param("password") String password);

    // 회원탈퇴 -> role update 해서 비활성화, 로그인,회원가입에서 걸러줘야함
    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    public void deleteAccount(String userId);
}
