package com.kiki.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.user.model.UserDto;
import com.kiki.user.model.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	UserService userService;
		
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String authenticateUser(UserDto loginForm, Model model, HttpSession session) {
		String id = loginForm.getId();
		String password = loginForm.getPassword();
		try {
			UserDto userDto = userService.authenticateUser(id, password);
			System.out.println(userDto);
			if (userDto != null) {
				userDto.setPassword(null);
				session.setAttribute("userInfo", userDto);
				return "index";
			} 
			model.addAttribute("loginMsg", "아이디 또는 비밀번호가 잘못되었습니다.");				
			return "user/login";
		} catch(Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/register")
	public String register() {
		return "user/register";
	}
	
	@PostMapping("/register")
	public String registerUser(UserDto registerForm, Model model) {
		try {
			System.out.println("heyhey");
			int valid = userService.registerUser(registerForm);
			if (valid == 1) {
				model.addAttribute("registerMsg", "회원가입 성공");
				return "redirect:/user/login";
			} 
			model.addAttribute("registerMsg", "회원가입에 실패하였습니다.");
			return "user/register";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
}
