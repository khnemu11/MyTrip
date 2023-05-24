package com.kiki.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.service.ReviewService;
import com.kiki.route.model.SearchDto;

@RestController
@RequestMapping("reviews")
public class ReviewRestController {
	
	ReviewService reviewService;
	
	@Autowired
	public ReviewRestController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchReview(@RequestParam("pageNo") int pageNo, @RequestParam("keyword") String keyword) {
		try {
			SearchDto search = new SearchDto(pageNo, keyword);
			System.out.println(search.getPageNo() + " " + search.getKeyword());
			search.setPageSize(20);
			search.setOffset((search.getPageNo()-1)*search.getPageSize());
			List<ReviewDto> reviewList = reviewService.searchReview(search);
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
