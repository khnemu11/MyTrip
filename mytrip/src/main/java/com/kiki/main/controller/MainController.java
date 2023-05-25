package com.kiki.main.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kiki.favorite.model.service.FavoriteService;
import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.service.ReviewService;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.service.TourService;

@RequestMapping("/")
@Controller
public class MainController {
	private FavoriteService favoriteService;
	private TourService tourService;
	private ReviewService reviewService;
	private ResourceLoader resourceLoader;
	
	@Autowired
	public MainController(FavoriteService favoriteService, TourService tourService, ReviewService reviewService,
			ResourceLoader resourceLoader) {
		super();
		this.favoriteService = favoriteService;
		this.tourService = tourService;
		this.reviewService = reviewService;
		this.resourceLoader = resourceLoader;
	}

	@GetMapping("/")
	public String index(Model model) {
		try {
			List<TourDto> tourList = tourService.selectHotTourList();
			model.addAttribute("tourList", tourList);
			System.out.println(tourList);

			List<TourDto> favoriteList = favoriteService.selectMostFavorite();
			model.addAttribute("favoriteList", favoriteList);
			
			SearchDto searchDto = new SearchDto();
			searchDto.setPageSize(5);
			searchDto.setKeyword("");
			searchDto.setPageNo(1);
			
			List<ReviewDto> reviewList = reviewService.searchReview(searchDto);
			System.out.println(reviewList);
			
			model.addAttribute("reviewList", reviewList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "index";
	}

	@GetMapping("/errorPage")
	public String error(Model model) {

		return "error/error";
	}

	@GetMapping("/uploadForm")
	public String upload(Model model) {

		return "review/uploadTest";
	}

	@PostMapping("/upload")
	public String upload(Model model, @RequestParam("file") MultipartFile multipartFile,
			@RequestParam("title") String name) {
		System.out.println(multipartFile);
		System.out.println(multipartFile.getOriginalFilename());
		System.out.println(name);

		String originalName = multipartFile.getOriginalFilename();
		String extend = originalName.substring(originalName.lastIndexOf('.'));
//		multipartFile.transferTo();
		UUID uuid = UUID.randomUUID();
		try {
			System.out.println(resourceLoader.getResource("classpath:/static/assets").getFile().getAbsolutePath());
			System.out.println(resourceLoader.getResource("classpath:/static/assets/img").getFile().getAbsolutePath());
			System.out.println(
					resourceLoader.getResource("classpath:/static/assets/img/upload").getFile().getAbsolutePath());

			File newFile = new File(
					resourceLoader.getResource("classpath:/static/assets/img/upload/").getFile().getAbsolutePath(),
					uuid.toString() + extend );
			System.out.println(uuid+extend);

//			File path = resourceLoader.getResource("classpath:/static/assets/img/upload").getFile();
			multipartFile.transferTo(newFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "review/uploadTest";
	}
}
