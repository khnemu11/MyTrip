package com.kiki.tour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.service.TourService;

@Controller
@RequestMapping("tour")
public class TourController {
	TourService tourService;

	@Autowired
	public TourController(TourService tourService) {
		super();
		this.tourService = tourService;
	}

	@GetMapping("/detail")
	public String detail(TourDto tourDto, Model model) {
		System.out.println("투어 상세 페이지 시작");

		System.out.println(tourDto);
		try {
			TourDto target = tourService.selectTour(tourDto);
			System.out.println("관광지 대상 : " + target);

			model.addAttribute("tour", target);
		} catch (Exception e) {
			return "error/error";

		}

		return "tour/tourDetailView";
	}

	@GetMapping("/search")
	public String search() {
		System.out.println("투어 검색 뷰 시작");
		return "tour/tourSearchView";
	}
}
