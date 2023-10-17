package com.example.unipet.mypage.dao;

import com.example.unipet.mypage.domain.MypetDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MyPetMapper {

    // 나의 펫
    @Select("SELECT pet_id, pet_name, pet_birth, pet_gender, pet_kind, pet_neuter, pet_pic, pet_color, pet_weight, pet_trait " +
            "FROM mypet " +
            "WHERE user_id = #{sessionId}")
    public MypetDTO showMyPet(@Param("sessionId") String sessionId);

    // 펫 정보 수정
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
            "WHERE user_id = #{sessionId}"
    })
    public boolean petInfoChange(@Param("dto") MypetDTO dto, @Param("sessionId") String sessionId);
    
    // 펫 등록
    @Insert("INSERT INTO mypet (user_id, pet_pic, pet_name, pet_birth, pet_gender, pet_kind, pet_neuter, pet_color, pet_weight, pet_trait) " +
            "VALUES (#{sessionId}, #{dto.pet_pic}, #{dto.pet_name}, #{dto.pet_birth}, #{dto.pet_gender}, #{dto.pet_kind}, #{dto.pet_neuter}, #{dto.pet_color}, #{dto.pet_weight}, #{dto.pet_trait})")
    public boolean petInfoAdd(@Param("dto") MypetDTO dto, @Param("sessionId") String sessionId);

    // 펫 삭제
    @Delete("DELETE FROM mypet WHERE user_id = #{sessionId}")
    public void deletePet(@Param("sessionId") String sessionId);
}
