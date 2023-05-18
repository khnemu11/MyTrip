package com.kiki.tour.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kiki.user.model.UserDto;
import com.kiki.user.model.service.UserService;

@Controller
@RequestMapping("tour")
public class TourController {
	@GetMapping("/search")
	public String search() {
		System.out.println("투어 검색 뷰 시작");
		
		return "tour/tourSearchView";
	}
}
