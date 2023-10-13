package com.example.unipet.signup.dao;

import com.example.unipet.signup.model.SignupDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SignupMapper {
//	@Insert("insert into reply (name, content, refid) values (#{name}, #{content}, #{refid})")
//	public boolean insertSignup(SignupDTO vo);

	@Select("SELECT MAX(id) FROM book;\n")
	public int insertSignup();
}
