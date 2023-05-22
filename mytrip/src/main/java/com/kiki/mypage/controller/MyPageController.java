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
	
	@PostMapping("/update")
	public String updateMyPage(UserDto updateForm, HttpSession session, Model model) {
		
		try {
			UserDto sessionUser = (UserDto) session.getAttribute("userInfo");
			updateForm.setId(sessionUser.getId());
			updateForm.setJoinDate(sessionUser.getJoinDate());
			if (updateForm.getPassword() == "") {
				updateForm.setPassword(sessionUser.getPassword());
			}
			int valid = myPageService.updateMyPage(updateForm);
			if (valid > 0) {
				model.addAttribute("mypageMsg", "수정 성공했습니다.");
				session.setAttribute("userInfo", updateForm);
				return "mypage/mypage";
			} else {
				model.addAttribute("mypageMsg", "수정 실패하였습니다.");
				return "mypage/update";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@GetMapping("/withdrawal")
	public String withdrawal() {
		return "mypage/withdrawal";	
	}
	
}

