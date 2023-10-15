package com.example.unipet.signup.dao;

import com.example.unipet.signup.model.SignupDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SignupMapper {
//	@Insert("insert into reply (name, content, refid) values (#{name}, #{content}, #{refid})")
//	public boolean insertSignup(SignupDTO vo);

	@Select("select count(myPet_id) from mypet;")
	public int insertSignup();
}
