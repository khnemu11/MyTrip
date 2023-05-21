package com.kiki.mypage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.mypage.model.dao.MyPageDao;
import com.kiki.user.model.UserDto;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	MyPageDao dao;
	
	@Override
	public int authenticateUser(String password) {
		return dao.authenticateUser(password);
	}

	@Override
	public int updateMyPage(String id, UserDto updateForm) {
		return dao.updateMyPage(id, updateForm);
	}

}
