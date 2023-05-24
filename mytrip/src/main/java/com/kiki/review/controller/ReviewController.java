package com.kiki.review.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.ReviewImgDto;
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
	public String list(Model model) {
		try {
			List<ReviewDto> list = reviewService.getList();
			System.out.println("controller 성공");
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			
			model.addAttribute("reviewList", list);
			return "review/list";

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("controller 에러났어요");
			return "error/error";
		}
		
	}
	
	@GetMapping("/write")
	public String write() {
		return "review/write";
	}
	
	@PostMapping("/write")
	public String writeReview(@ModelAttribute("reviewForm") ReviewDto reviewForm, HttpSession session) {
		try {
			String name = ((UserDto)session.getAttribute("userInfo")).getName();
			reviewForm.setUserName(name);
			System.out.println(reviewForm);
			int valid = reviewService.writeReview(reviewForm);
			if(valid > 0) {
				System.out.println("리뷰쓰기 성공");
				return "review/detail";
			} else {
				System.out.println("리뷰쓰기 실패");
				return "review/write";
			}
		} catch(Exception e) {
			System.out.println("리뷰쓰기 에러");
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@GetMapping("/detail/{seq}")
	public String detail(@PathVariable("seq") int seq, Model model) {
		try {
			// 장소 처리도 해야해용
			ReviewDto review = reviewService.getReviewDetail(seq);
			System.out.println("리뷰 스타뚜");
			System.out.println(review);
			model.addAttribute("review", review);
			List<ReviewImgDto> reviewImg = reviewService.getReviewImg(seq);
			model.addAttribute("reviewImg", reviewImg);
			System.out.println("리뷰이미지 스타뚜");
			for(int i=0; i<reviewImg.size(); i++) {
				System.out.println(i + " " + reviewImg);
			}
			return "review/detail";
		} catch(Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
}
