package com.kiki.review.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.ReviewImgDto;
import com.kiki.review.model.service.ReviewService;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.service.TourService;
import com.kiki.user.model.UserDto;

@Controller
@RequestMapping("review")
public class ReviewController {
	ReviewService reviewService;
	TourService tourService;
	ResourceLoader resourceLoader;
	
	@Autowired
	public ReviewController(ReviewService reviewService, TourService tourService, ResourceLoader resourceLoader) {
		super();
		this.reviewService = reviewService;
		this.tourService = tourService;
		this.resourceLoader = resourceLoader;
	}
	@GetMapping("/listReview")
	public String listPlan(HttpServletRequest request, HttpSession session, Model model, SearchDto searchDto) {
		System.out.println("후기 리스트 시작");
		searchDto.setPageSize(10);
		if (searchDto.getPageNo() == 0) {
			System.out.println("비어있음!");
			searchDto.setPageNo(1);
		}
		searchDto.setOffset((searchDto.getPageNo() - 1) * searchDto.getPageSize());
		searchDto.setUserId(((UserDto) session.getAttribute("userInfo")).getId());
		System.out.println("검색 dto");
		System.out.println((UserDto) session.getAttribute("userInfo"));
		System.out.println(searchDto);

		List<ReviewDto> list = reviewService.selectReviewList(searchDto);

		if (list == null) {
			System.out.println("허용되지 않는 범위");
			return "redirect:/route/listPan?pageNo=1";
		}

		System.out.println(list);
		Integer totalCount = reviewService.countReviewList(searchDto);
		if (totalCount == null) {
			totalCount = 0;
		}
		System.out.println(totalCount);
		searchDto.setTotalCount(totalCount);

		if (searchDto.getPageNo() != 1) {
			searchDto.setBefore(true);
		}

		searchDto.setStart(searchDto.getPageNo());
		searchDto.setEnd(searchDto.getPageNo());

		for (int i = 1; i < 5; i++) {
			if (searchDto.getOffset() + searchDto.getPageSize() * i > totalCount) {
				break;
			}
			searchDto.setEnd(searchDto.getEnd() + 1);
		}

		if (searchDto.getTotalCount() > searchDto.getPageSize() * searchDto.getEnd()) {
			searchDto.setNext(true);
		}

		System.out.println(searchDto);

		model.addAttribute("list", list);
		model.addAttribute("pagination", searchDto);

		return "review/myReviewListView";
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
	public String writeReview( @RequestParam Map<String, String> paramMap,
			@ModelAttribute("reviewForm") ReviewDto reviewForm, @RequestParam("file") MultipartFile multipartFile, HttpSession session) {
		try {
			//여행 객체 생성
			TourDto tourDto = new TourDto();
			tourDto.setAddress(paramMap.get("tour-address"));
			tourDto.setLongitude(Float.parseFloat(paramMap.get("tour-longitude")));
			tourDto.setLatitude(Float.parseFloat(paramMap.get("tour-latitude")));
			tourDto.setTelephone(paramMap.get("tour-telephone"));
			tourDto.setTitle(paramMap.get("tour-title"));
				
			//리뷰 객체 생성
			String name = ((UserDto) session.getAttribute("userInfo")).getName();
			String id = ((UserDto) session.getAttribute("userInfo")).getId();
			reviewForm.setUserName(name);
			reviewForm.setUserId(id);
			reviewForm.setTourTitle(tourDto.getTitle());
			
			System.out.println(reviewForm);
			;
			//리뷰 및 여행 등록
			int valid = reviewService.writeReview(reviewForm, tourDto);
			
			//리뷰 성공
			if (valid > 0) {
			    //파일 등록
				
				//리뷰 seq	
				int seq = reviewService.getLastestReview(id);
				System.out.println("리뷰 seq : "+seq);
				
				String originalName = multipartFile.getOriginalFilename();
				String extend = originalName.substring(originalName.lastIndexOf('.'));
				UUID uuid = UUID.randomUUID();
				File newFile = new File(
						resourceLoader.getResource("classpath:/static/assets/img/upload/").getFile().getAbsolutePath(),
						uuid.toString() + extend);
	
				multipartFile.transferTo(newFile);
				System.out.println("파일 저장 성공!");
				
				System.out.println("바뀐 이름 : "+uuid+extend);
				System.out.println("원래 이름 : "+originalName);
				
				ReviewImgDto imgDto = new ReviewImgDto();
				imgDto.setImageCode(uuid+extend);
				imgDto.setImageName(originalName);
				imgDto.setReviewSeq(seq);
				
				reviewService.insertImage(imgDto);
				System.out.println("리뷰쓰기 성공");
				// 가장 최신 것 중에서 아이디 같은 것
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
				String tmp = reviewImg.get(i).getImageCode();
				System.out.println(reviewImg.get(i).getImageCode()+"   길이 : "+tmp.length());
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
			@RequestParam Map<String, String> paramMap, HttpSession session, Model model) {
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
				List<ReviewImgDto> reviewImg = reviewService.getReviewImg(seq);
				model.addAttribute("reviewImg", reviewImg);
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
