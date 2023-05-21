package com.kiki.mypage.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageDao {

	public int authenticateUser(String password);
	
}
