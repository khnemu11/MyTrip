package com.kiki.route.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@GetMapping("/findRouteView")
	public String findRouteView(HttpServletRequest request, Model model) {
		System.out.println("경로 검색 페이지 시작");
		
		return "route/findRouteView";
	}
	@GetMapping("/registRouteView")
	public String registRouteView(HttpServletRequest request, Model model) {
		System.out.println("경로 상세 페이지 시작");
		String titleArr[] = request.getParameterValues("title");
		String latitudeArr[] = request.getParameterValues("latitude");
		String longitudeArr[] = request.getParameterValues("longitude");
		
		List<TourDto> tourList= new ArrayList<>();
		
		for(int i=0;i<titleArr.length;i++) {
			TourDto tour = new TourDto();
			
			tour.setTitle(titleArr[i]);
			tour.setLatitude(Float.parseFloat(latitudeArr[i]));
			tour.setLongitude(Float.parseFloat(longitudeArr[i]));
			System.out.println(tour);
			tourList.add(tour);
		}
		
		model.addAttribute("tourList",tourList);
		
		return "route/registRouteView";
	}
	@GetMapping("/findRoute")
	@ResponseBody
	public ResponseEntity<?> registRoute(HttpServletRequest request, Model model) {
		System.out.println("경로 탐색 시작");
		String titleArr[] = request.getParameterValues("title");
		String latitudeArr[] = request.getParameterValues("latitude");
		String longitudeArr[] = request.getParameterValues("longitude");
		System.out.println(Arrays.toString(titleArr));
		String data = "";

		JSONObject jsonData = new JSONObject();
		JSONObject tourObject = new JSONObject();
		JSONObject infoObject = new JSONObject();
		JSONArray waypoints = new JSONArray();
		try {
			for (int i = 0; i < titleArr.length; i++) {
				infoObject = new JSONObject();
				infoObject.put("x", longitudeArr[i]);
				infoObject.put("y", latitudeArr[i]);
				infoObject.put("name", "a");
				if (i == 0) {
					jsonData.put("origin", infoObject);
				} else if (i == titleArr.length - 1) {
					jsonData.put("destination", infoObject);
				} else {
					waypoints.add(infoObject);
				}
				System.out.println(infoObject.toString());
			}
			jsonData.put("waypoints", waypoints);
			jsonData.put("priority", "RECOMMEND");
			jsonData.put("car_fuel", "GASOLINE");
			jsonData.put("car_hipass", "false");
			jsonData.put("alternatives", "false");
			jsonData.put("road_details", "false");

			System.out.println(jsonData);

			URL url = new URL("https://apis-navi.kakaomobility.com/v1/waypoints/directions");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("Authorization", "KakaoAK 6154fef5b7c1b59c554c60cef67c2ba5");

			http.setDoOutput(true); // outputstream으로 post데이터를 넘겨준다는 옵션

			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(http.getOutputStream(), "UTF-8"));
			printWriter.write(jsonData.toString());
			System.out.println(jsonData.toString());
			printWriter.flush();
			BufferedReader br = null;
			int status = http.getResponseCode();
			if (status == HttpURLConnection.HTTP_OK) {
				System.out.println("카카오 길찾기 수신 성공!");

				br = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
			} else {
				System.out.println("카카오 길찾기 수신 실패!");
				br = new BufferedReader(new InputStreamReader(http.getErrorStream(), "UTF-8"));
			}
			StringBuilder responseBody = new StringBuilder();
			String line = "";

			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			br.close();
			System.out.println("길찾기 결과 " + responseBody);
			JSONParser parser = new JSONParser();
			JSONObject responseJson = (JSONObject) parser.parse(responseBody.toString());
			JSONObject routes = (JSONObject) ((JSONArray) responseJson.get("routes")).get(0);

			model.addAttribute("route", routes);
			data = routes.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}

	@GetMapping("/search")
	public String search() {
		System.out.println("투어 검색 뷰 시작");
		return "tour/tourSearchView";
	}
}
