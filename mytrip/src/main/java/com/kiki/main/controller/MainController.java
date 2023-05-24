package com.kiki.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.favorite.model.service.FavoriteService;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.service.TourService;

@RequestMapping("/")
@Controller
public class MainController {
	FavoriteService favoriteService;
	TourService tourService;

	@Autowired
	public MainController(FavoriteService favoriteService, TourService tourService) {
		super();
		this.favoriteService = favoriteService;
		this.tourService = tourService;
	}

	@GetMapping("/")
	public String index(Model model) {
		try {
			List<TourDto> tourList = tourService.selectHotTourList();
			model.addAttribute("tourList", tourList);
			System.out.println(tourList);
			
			List<TourDto> favoriteList = favoriteService.selectMostFavorite();
			model.addAttribute("favoriteList", favoriteList);
			System.out.println(favoriteList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "index";
	}
}
