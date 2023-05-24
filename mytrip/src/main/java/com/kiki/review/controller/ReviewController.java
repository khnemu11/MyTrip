package com.kiki.review.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.ReviewImgDto;
import com.kiki.review.model.service.ReviewService;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.service.TourService;
import com.kiki.user.model.UserDto;

@Controller
@RequestMapping("review")
public class ReviewController {
	ReviewService reviewService;
	TourService tourService;

	@Autowired
	public ReviewController(ReviewService reviewService, TourService tourService) {
		super();
		this.reviewService = reviewService;
		this.tourService = tourService;
	}

	@GetMapping("/list")
	public String list(Model model) {
		try {
			List<ReviewDto> list = reviewService.getList();
			System.out.println("controller 성공");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}

			model.addAttribute("reviewList", list);
			return "review/list";

		} catch (Exception e) {
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
	public String writeReview(HttpServletRequest request, @RequestParam Map<String, String> paramMap,
			@ModelAttribute("reviewForm") ReviewDto reviewForm, HttpSession session) {
		try {
			TourDto tourDto = new TourDto();
			tourDto.setAddress(paramMap.get("tour-address"));
			tourDto.setLongitude(Float.parseFloat(paramMap.get("tour-longitude")));
			tourDto.setLatitude(Float.parseFloat(paramMap.get("tour-latitude")));
			tourDto.setTelephone(paramMap.get("tour-telephone"));
			tourDto.setTitle(paramMap.get("tour-title"));
			
			String name = ((UserDto) session.getAttribute("userInfo")).getName();
			String id = ((UserDto) session.getAttribute("userInfo")).getId();
			reviewForm.setUserName(name);
			reviewForm.setUserId(id);
			reviewForm.setTourTitle(tourDto.getTitle());
			System.out.println(reviewForm);
			int valid = reviewService.writeReview(reviewForm, tourDto);
			if (valid > 0) {
				System.out.println("리뷰쓰기 성공");
				// 가장 최신 것 중에서 아이디 같은 것
				int seq = reviewService.getLastestReview(id);
				System.out.println(seq);
				return "redirect:detail/" + seq;
			} else {
				System.out.println("리뷰쓰기 실패");
				return "review/write";
			}
		} catch (Exception e) {
			System.out.println("리뷰쓰기 에러");
			e.printStackTrace();
			return "error/error";
		}
	}

	@GetMapping("/detail/{seq}")
	public String detail(@PathVariable("seq") int seq, Model model) {
		try {
			System.out.println(model.getAttribute("reviewMsg"));
			ReviewDto review = reviewService.getReviewDetail(seq);
			
			System.out.println("리뷰 스타뚜");
			System.out.println(review);
			model.addAttribute("review", review);
			List<ReviewImgDto> reviewImg = reviewService.getReviewImg(seq);
			model.addAttribute("reviewImg", reviewImg);
			System.out.println("리뷰이미지 스타뚜");
			for (int i = 0; i < reviewImg.size(); i++) {
				System.out.println(i + " " + reviewImg);
			}
			return "review/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}

	}
	
	@GetMapping("/update/{seq}")
	public String update(@PathVariable("seq") int seq, Model model) {
		try {
			ReviewDto review = reviewService.getReviewDetail(seq);
			model.addAttribute("review", review);
			List<ReviewImgDto> reviewImg = reviewService.getReviewImg(seq);
			model.addAttribute("reviewImg", reviewImg);
			
			TourDto searchTourDto = new TourDto();
			searchTourDto.setTitle(review.getTourTitle());
			TourDto tourDto = tourService.selectTourByTitle(searchTourDto);
			model.addAttribute("tour", tourDto);
			
			return "review/update";
		} catch(Exception e) {
			return "error/error";
		}
	}
	
	@PostMapping("/update")
	public String updateReview(@ModelAttribute("reviewForm") ReviewDto reviewForm,
			@RequestParam Map<String, String> paramMap, HttpSession session) {
		try {
			TourDto tourDto = new TourDto();
			tourDto.setAddress(paramMap.get("tour-address"));
			tourDto.setLongitude(Float.parseFloat(paramMap.get("tour-longitude")));
			tourDto.setLatitude(Float.parseFloat(paramMap.get("tour-latitude")));
			tourDto.setTelephone(paramMap.get("tour-telephone"));
			tourDto.setTitle(paramMap.get("tour-title"));
			String name = ((UserDto) session.getAttribute("userInfo")).getName();
			String id = ((UserDto) session.getAttribute("userInfo")).getId();
			reviewForm.setUserName(name);
			reviewForm.setUserId(id);
			reviewForm.setTourTitle(tourDto.getTitle());
			System.out.println(reviewForm);
			int valid = reviewService.writeReview(reviewForm,tourDto);
			if (valid > 0) {
				System.out.println("리뷰수정 성공");
				// 가장 최신 것 중에서 아이디 같은 것
				int seq = reviewService.getLastestReview(id);
				System.out.println(seq);
				return "redirect:detail/" + seq;
			} else {
				System.out.println("리뷰쓰기 실패");
				return "review/write";
			}
		} catch (Exception e) {
			System.out.println("리뷰쓰기 에러");
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@GetMapping("/delete/{seq}")
	public String delete(@PathVariable("seq") int seq, Model model) {
		try {
			System.out.println("삭제 시작!");
			int valid = reviewService.deleteReview(seq);
			if (valid > 0) {
				System.out.println("삭제 성공");
				model.addAttribute("reviewMsg", "삭제 성공!");
				return "review/list";
			} else {
				System.out.println("삭제 실패");
				model.addAttribute("reviewMsg", "삭제 실패하였습니다.");
				return "redirect:review/detail/"+seq;
			}
		} catch (Exception e) {
			System.out.println("삭제 에러");
			e.printStackTrace();
			return "error/error";
		}
	}
	
}
