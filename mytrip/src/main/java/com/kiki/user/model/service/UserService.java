package com.kiki.user.model.service;


import java.util.List;

import com.kiki.user.model.UserDto;


public interface UserService {
	public List<UserDto> selectUserList();
	public UserDto authenticateUser(String id, String password);
	public int registerUser(UserDto registerForm);
	public int checkId(String id);
}
