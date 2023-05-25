package com.kiki.favorite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.favorite.model.service.FavoriteService;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.service.TourService;
import com.kiki.user.model.UserDto;

@RequestMapping("favorite")
@Controller
public class FavoriteController {
	FavoriteService favoriteService;
	TourService tourService;
	
	@Autowired
	public FavoriteController(FavoriteService favoriteService,TourService tourService) {
		super();
		this.favoriteService = favoriteService;
		this.tourService = tourService;
	}

	@GetMapping("/favoriteList")
	public String favorite(SearchDto searchDto, HttpSession session,Model model) {
		System.out.println("경로 리스트 시작");
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

		List<TourDto> list = favoriteService.selectFavoriteList(searchDto);

		if (list == null) {
			System.out.println("허용되지 않는 범위");
			return "redirect:/route/listPan?pageNo=1";
		}

		System.out.println(list);
		Integer totalCount = favoriteService.countFavoriteList(searchDto);
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

		return "favorite/listFavoriteView";
	}
}
