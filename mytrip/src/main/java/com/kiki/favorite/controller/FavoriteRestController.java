package com.kiki.favorite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiki.favorite.model.FavoriteDto;
import com.kiki.favorite.model.service.FavoriteService;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.service.TourService;
import com.kiki.user.model.UserDto;

@RequestMapping("favorites")
@RestController
public class FavoriteRestController {
	FavoriteService favoriteService;
	TourService tourService;
	@Autowired
	public FavoriteRestController(FavoriteService favoriteService,TourService tourService) {
		super();
		this.favoriteService = favoriteService;
		this.tourService = tourService;
	}

	@GetMapping("/{title}")
	public String favorite(@PathVariable("title") String title, HttpSession session) {
		System.out.println(title);
		String result = "";
		try {
			FavoriteDto favoriteDto = new FavoriteDto();

			favoriteDto.setUserId(((UserDto) session.getAttribute("userInfo")).getId());
//			favoriteDto.setUserId("test");
			favoriteDto.setTitle(title);

			int isFavorite = favoriteService.isFavorite(favoriteDto);
			System.out.println("isFavorite : "+isFavorite);
			TourDto searchTourDto = new TourDto();
			searchTourDto.setTitle(title);
			TourDto tourdto = tourService.selectTourByTitle(searchTourDto);
			favoriteDto.setTourSeq(tourdto.getSeq());
			System.out.println("tourdto : "+tourdto);
			if (isFavorite > 0) {
				System.out.println("삭제");
				favoriteService.deleteFavorite(favoriteDto);
				result = "delete";
			}else {
				System.out.println("등록");
				favoriteService.registFavorite(favoriteDto);
				result = "regist";
			}

		} catch (Exception e) {
			e.printStackTrace();

			result = "error";
		}

		return result;
	}
}
