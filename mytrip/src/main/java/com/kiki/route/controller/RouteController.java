package com.kiki.route.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.service.TourService;

@Controller
@RequestMapping("route")
public class RouteController {
	TourService tourService;
	@Value("${youtube.key}")
	private String youtubeKey;

	@Autowired
	public RouteController(TourService tourService) {
		super();
		this.tourService = tourService;
	}
	
	@GetMapping("/findRoute")
	public String detail(TourDto tourDto, Model model){
		System.out.println("경로 상세 페이지 시작");
		
		return "route/findRouteView";
	}

	@GetMapping("/search")
	public String search() {
		System.out.println("투어 검색 뷰 시작");
		return "tour/tourSearchView";
	}
}
