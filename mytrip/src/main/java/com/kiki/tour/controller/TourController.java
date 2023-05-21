package com.kiki.tour.controller;

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
@RequestMapping("tour")
public class TourController {
	TourService tourService;
	@Value("${youtube.key}")
	private String youtubeKey;

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
//			String encodeTitle = new String(target.getTitle().getBytes(),"UTF-8");
			
			URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&q=" +  URLEncoder.encode(target.getTitle(), "utf-8")
					+ "&regionCode=kr&order=viewCount&maxResults=5&key=" + youtubeKey);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			String input = "";
			String JSONInput = "";
			while ((input = br.readLine()) != null) {
				JSONInput +=input;
			}
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(JSONInput);
			System.out.println(jsonObj);
			JSONArray jsonArr = (JSONArray) jsonObj.get("items");

			if(jsonArr.size()>0) {
				ArrayList<String> youtubeList = new ArrayList<>();
				for (int i = 0; i < jsonArr.size(); i++) {
					JSONObject item = (JSONObject)jsonArr.get(i);
					JSONObject thumbnail =(JSONObject) ((JSONObject)item.get("snippet")).get("thumbnails");
					String img =(String) ((JSONObject)thumbnail.get("high")).get("url");
					youtubeList.add(img);
					
				}
				
				model.addAttribute("youtubeList",youtubeList);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
