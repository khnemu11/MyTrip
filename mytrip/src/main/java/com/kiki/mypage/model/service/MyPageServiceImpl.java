package com.kiki.mypage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.mypage.model.dao.MyPageDao;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	MyPageDao dao;
	
	@Override
	public int authenticateUser(String password) {
		return dao.authenticateUser(password);
	}

}
