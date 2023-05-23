package com.kiki.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.service.ReviewService;

@RestController
@RequestMapping("reviews")
public class ReviewRestController {
	
	ReviewService reviewService;
	
	@Autowired
	public ReviewRestController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> searchReview(@RequestBody Map<String, String> keyword) {
		try {
			System.out.println(keyword);
			System.out.println(keyword.get("keyword"));
			List<ReviewDto> reviewList = reviewService.searchReview(keyword.get("keyword"));
			for(int i=0; i<reviewList.size(); i++) {
				System.out.println(reviewList.get(i));
			}
			System.out.println("restController 성공!");
			return new ResponseEntity<List<ReviewDto>>(reviewList, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("restController 에러낫어용!");
			e.printStackTrace();
			return new ResponseEntity<String>("오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
