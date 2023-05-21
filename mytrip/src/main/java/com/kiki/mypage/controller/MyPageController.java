package com.kiki.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kiki.mypage.model.service.MyPageService;
import com.kiki.user.model.UserDto;

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
	
	@GetMapping("/update")
	public String update() {
		return "mypage/update";
	}
	
	@PutMapping("/update")
	public String update(UserDto updateForm, HttpSession session, Model model) {
		String id = session.getId();
		try {
			int valid = myPageService.updateMyPage(id, updateForm);
			if (valid > 0) {
				System.out.println("성공");
				model.addAttribute("mypageMsg", "수정 성공했습니다.");
				return "mypage/mypage";
			} else {
				System.out.println("실패");
				model.addAttribute("mypageMsg", "수정 실패하였습니다.");
				return "mypage/update";
			}
		} catch (Exception e) {
			System.out.println("에러");
			e.printStackTrace();
			return "error/error";
		}
		
	}
	
}

