package com.kiki.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.user.model.service.UserService;

@Controller
@RequestMapping("mypage")
public class MyPageController {
	@Autowired
	UserService userService;
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage/mypage"; // id로 이동할 수 있도록 처리  // 계정과 일치하는 사람만 이동하도록 처리
	}
}