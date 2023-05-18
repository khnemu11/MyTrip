package com.kiki.user.model.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.user.model.UserDto;
import com.kiki.user.model.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;
	
	@Override
	public List<UserDto> selectUserList() {
		return dao.selectUsers();
	}
}
