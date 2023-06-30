package com.kiki.review.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.review.model.ReplyDto;
import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.service.ReplyService;
import com.kiki.review.model.service.ReviewService;
import com.kiki.route.model.SearchDto;
import com.kiki.user.model.UserDto;

@RestController
@RequestMapping("reviews")
public class ReviewRestController {
	ReplyService replyService;
	ReviewService reviewService;
	
	@Autowired
	public ReviewRestController(ReviewService reviewService,ReplyService replyService) {
		super();
		this.replyService = replyService;
		this.reviewService = reviewService;
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchReview(@RequestParam("pageNo") int pageNo, @RequestParam("keyword") String keyword) {
		try {
			SearchDto search = new SearchDto();
			search.setKeyword(keyword);
			search.setPageNo(pageNo);
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
	
	@GetMapping("/registReply")
	public ResponseEntity<?> registReply(ReplyDto replyDto,HttpSession session) {
		System.out.println("registReply 시작");
		try {
			System.out.println(replyDto);
			if(session.getAttribute("userInfo") == null) {
				return new ResponseEntity<String>("로그인 후 이용 가능합니다.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			replyDto.setWriter(((UserDto)session.getAttribute("userInfo")).getName());
			replyService.writeReply(replyDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("restController 에러낫어용!");
			e.printStackTrace();
			return new ResponseEntity<String>("덧글 작성 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/deleteReply")
	public ResponseEntity<?> deleteReply(ReplyDto replyDto,HttpSession session) {
		System.out.println("deleteReply 시작");
		try {
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("덧글 삭제 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
