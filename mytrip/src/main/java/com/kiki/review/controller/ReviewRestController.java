package com.kiki.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.service.ReviewService;

@RestController
@RequestMapping("users")
public class ReviewRestController {
	
	ReviewService reviewService;
	
	@Autowired
	public ReviewRestController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> searchReview(@ModelAttribute("keyword") String keyword) {
		try {
			List<ReviewDto> reviewList = reviewService.searchReview(keyword);
			for(int i=0; i<reviewList.size(); i++) {
				System.out.println(reviewList.get(i));
			}
			if (reviewList.size() > 0) {
				System.out.println("성공");
				return new ResponseEntity<List<ReviewDto>>(reviewList, HttpStatus.OK);
			}
			System.out.println("검색 결과 엄슴!");
			return new ResponseEntity<String>("검색 결과가 없습니다.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
