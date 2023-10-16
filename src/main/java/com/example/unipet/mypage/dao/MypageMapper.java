package com.example.unipet.mypage.dao;

import com.example.unipet.mypage.domain.MypetDTO;
import com.example.unipet.mypage.domain.UserDTO;
import com.example.unipet.mypage.domain.UserVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MypageMapper {

    // 메인
    @Select("SELECT name FROM user WHERE user_id = #{sessionId}")
    public String getMyName(@Param("sessionId") String sessionId);

    // 회원 정보
    @Select("SELECT user_id, password, email, name FROM user WHERE user_id = #{sessionId}")
    public UserVO getMyInfo(@Param("sessionId") String sessionId);

    // 나의 펫
    @Select("SELECT myPet_id, pet_name, pet_birth, pet_gender, pet_kind, pet_neuter, pet_pic, pet_color, pet_weight, pet_trait " +
            "FROM mypet " +
            "WHERE User_user_id = #{sessionId}")
    public MypetDTO showMyPet(@Param("sessionId") String sessionId);

    @Update({
            "UPDATE mypet",
            "SET pet_name = #{dto.pet_name},",
            "    pet_birth = #{dto.pet_birth},",
            "    pet_gender = #{dto.pet_gender},",
            "    pet_kind = #{dto.pet_kind},",
            "    pet_neuter = #{dto.pet_neuter},",
            "    pet_pic = #{dto.pet_pic},",
            "    pet_color = #{dto.pet_color},",
            "    pet_weight = #{dto.pet_weight},",
            "    pet_trait = #{dto.pet_trait}",
            "WHERE User_user_id = #{sessionId}"
    })
    public boolean petInfoChange(@Param("dto") MypetDTO dto, @Param("sessionId") String sessionId);

    @Insert("INSERT INTO mypet (User_user_id, pet_pic, pet_name, pet_birth, pet_gender, pet_kind, pet_neuter, pet_color, pet_weight, pet_trait) " +
            "VALUES (#{sessionId}, #{dto.pet_pic}, #{dto.pet_name}, #{dto.pet_birth}, #{dto.pet_gender}, #{dto.pet_kind}, #{dto.pet_neuter}, #{dto.pet_color}, #{dto.pet_weight}, #{dto.pet_trait})")
    public boolean petInfoAdd(@Param("dto") MypetDTO dto, @Param("sessionId") String sessionId);

    @Delete("DELETE FROM mypet WHERE User_user_id = #{sessionId}")
    public void deletePet(@Param("sessionId") String sessionId);
}
