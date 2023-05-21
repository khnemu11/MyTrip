package com.kiki.mypage.model.service;

import com.kiki.user.model.UserDto;

public interface MyPageService {

	int authenticateUser(String password);

	int updateMyPage(String id, UserDto updateForm);
	
}
