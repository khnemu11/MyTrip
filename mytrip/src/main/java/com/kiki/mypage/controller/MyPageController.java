package com.kiki.mypage.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kiki.favorite.model.service.FavoriteService;
import com.kiki.mypage.model.service.MyPageService;
import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.service.ReviewService;
import com.kiki.route.model.PlanDto;
import com.kiki.route.model.SearchDto;
import com.kiki.route.model.service.PlanService;
import com.kiki.tour.model.TourDto;
import com.kiki.user.model.UserDto;

@Controller
@RequestMapping("mypage")
public class MyPageController {
	
	MyPageService myPageService;
	FavoriteService favoriteService;
	PlanService planService;
	ReviewService reviewService;
	

	@Autowired
	public MyPageController(MyPageService myPageService, FavoriteService favoriteService, PlanService planService,
			ReviewService reviewService) {
		super();
		this.myPageService = myPageService;
		this.favoriteService = favoriteService;
		this.planService = planService;
		this.reviewService = reviewService;
	}

	@GetMapping("/mypage")
	public String mypage(HttpSession session,Model model) {
		try {
			UserDto sessionUser = (UserDto) session.getAttribute("userInfo");
			LocalDateTime dateTime = sessionUser.getJoinDate().toLocalDateTime();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			sessionUser.setJoinDateStr( dateTime.format(format));
			
			SearchDto searchDto = new SearchDto();
			searchDto.setPageNo(1);
			searchDto.setPageSize(3);
			searchDto.setUserId(sessionUser.getId());
			List<TourDto> favoriteList= favoriteService.selectFavoriteList(searchDto);
			List<ReviewDto> reviewList= reviewService.selectReviewList(searchDto);
			List<PlanDto> planList= planService.selectPlanList(searchDto);
			
			
			model.addAttribute("favoriteList", favoriteList);
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("planList", planList);
		}catch(Exception e) {
			e.printStackTrace();
			return "error/error";
		}
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
				return "redirect:/mypage/mypage";
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

