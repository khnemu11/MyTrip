package com.kiki.user.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.user.model.UserDto;

@Mapper
public interface UserDao {
	public List<UserDto> selectUsers();

	public UserDto authenticateUser(String id, String password);

	public int registerUser(UserDto registerForm);
}
