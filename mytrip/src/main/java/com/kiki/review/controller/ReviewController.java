package com.kiki.review.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.service.ReviewService;
import com.kiki.user.model.UserDto;

@Controller
@RequestMapping("review")
public class ReviewController {

	ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@GetMapping("/list")
	public String list() {
		return "review/list";
	}
	
	@GetMapping("/write")
	public String write() {
		return "review/write";
	}
	
	@PostMapping("/write")
	public String writeReview(@ModelAttribute("reviewForm") ReviewDto reviewForm, HttpSession session) {
		try {
			String id = ((UserDto)session.getAttribute("userInfo")).getId();
			reviewForm.setUserId(id);
			int valid = reviewService.writeReview(reviewForm);
			if(valid > 0) {
				System.out.println("성공");
				return "review/detail";
			} else {
				System.out.println("실패");
				return "review/write";
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("에러");
			return "error/error";
		}
	}
}
