package com.kiki.user.model.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.user.model.UserDto;
import com.kiki.user.model.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public List<UserDto> selectUserList() {
		return userDao.selectUsers();
	}

	@Override
	public UserDto authenticateUser(String id, String password) {
		return userDao.authenticateUser(id, password);
	}

	@Override
	public int registerUser(UserDto registerForm) {
		return userDao.registerUser(registerForm);
	}

	@Override
	public int checkId(String id) {
		return userDao.checkId(id);
	}
}
