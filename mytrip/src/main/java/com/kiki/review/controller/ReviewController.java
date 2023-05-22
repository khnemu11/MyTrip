package com.kiki.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.review.model.service.ReviewService;

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
}
