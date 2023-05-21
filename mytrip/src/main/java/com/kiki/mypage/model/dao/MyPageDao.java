package com.kiki.mypage.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.user.model.UserDto;

@Mapper
public interface MyPageDao {

	public int authenticateUser(String password);

	public int updateMyPage(String id, UserDto updateForm);
	
}
