package com.kiki.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kiki.mypage.model.service.MyPageService;

@Controller
@RequestMapping("mypage")
public class MyPageController {
	@Autowired
	MyPageService myPageService;
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage/mypage";
	}
	
	@GetMapping("/authentication")
	public String authentication() {
		return "mypage/authentication";
	}
	
	@PostMapping("/authentication")
	public String authenticateUser(@RequestParam(value="password") String password, HttpSession session, Model model) {
		try {
			int validation = myPageService.authenticateUser(password);
			if (validation == 0) {
				model.addAttribute("mypageMsg", "비밀번호가 틀렸습니다.");
				return "mypage/authentication";
			} else {
				return "mypage/update";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
}

