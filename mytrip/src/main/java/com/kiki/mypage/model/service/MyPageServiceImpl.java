package com.kiki.mypage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.mypage.model.dao.MyPageDao;
import com.kiki.user.model.UserDto;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	MyPageDao myPageDao;
	
	@Override
	public int authenticateUser(String password) {
		return myPageDao.authenticateUser(password);
	}

	@Override
	public int updateMyPage(UserDto updateForm) {
		return myPageDao.updateMyPage(updateForm);
	}

	@Override
	public int withdrawUser(String id) {
		return myPageDao.withdrawUser(id);
	}

}
