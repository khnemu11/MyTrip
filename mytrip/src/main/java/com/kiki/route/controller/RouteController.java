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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kiki.route.model.PlanDto;
import com.kiki.route.model.PlanOrderDto;
import com.kiki.route.model.SearchDto;
import com.kiki.route.model.service.PlanService;
import com.kiki.tour.model.TourDto;
import com.kiki.user.model.UserDto;

@Controller
@RequestMapping("route")
public class RouteController {
	PlanService planService;

	@Value("${youtube.key}")
	private String youtubeKey;

	@Autowired
	public RouteController(PlanService planService) {
		super();
		this.planService = planService;
	}

	@GetMapping("/planDetail")
	public String planDetail(HttpServletRequest request, Model model, @RequestParam(name = "seq") int seq) {
		System.out.println(seq);

		PlanDto planDto = planService.selectPlan(seq);
		System.out.println(planDto);
		List<PlanOrderDto> planOrderDtoList = planService.selectPlanOrderList(seq);
		System.out.println(planOrderDtoList);

		model.addAttribute("plan", planDto);
		model.addAttribute("list", planOrderDtoList);

		return "/route/planDetailView";
	}

	@GetMapping("/listPlan")
	public String listPlan(HttpServletRequest request, HttpSession session, Model model, SearchDto searchDto) {
		System.out.println("경로 리스트 시작");

		if (searchDto.getPageNo() == 0) {
			System.out.println("비어있음!");
			searchDto.setPageNo(1);
		}
		searchDto.setOffset((searchDto.getPageNo()-1)*searchDto.getPageSize());
		System.out.println(searchDto);
		searchDto.setUserId(((UserDto) session.getAttribute("userInfo")).getId());
		List<PlanDto> list = planService.selectPlanList(searchDto);
		
		if(list==null) {
			System.out.println("허용되지 않는 범위");
			return "redirect:/route/listPan";
		}
		
		System.out.println(list);
		
		int totalCount = planService.countPlanList(searchDto);
		System.out.println(totalCount);
		searchDto.setTotalCount(totalCount);

		if(searchDto.getPageNo()!=1) {
			searchDto.setBefore(true);
		}
		searchDto.setStart(searchDto.getPageNo());
		searchDto.setEnd(searchDto.getPageNo());
		
		for (int i = 1; i < 5; i++) {
			if(searchDto.getOffset() + searchDto.getPageSize()* i >totalCount) {
				break;
			}
			searchDto.setEnd(searchDto.getEnd()+1);
		}

		if (searchDto.getTotalCount() > searchDto.getPageSize() * searchDto.getEnd()) {
			searchDto.setNext(true);
		}
		
		System.out.println(searchDto);
		
		model.addAttribute("list", list);
		model.addAttribute("pagination", searchDto);
		
		return "route/listPlanView";
	}

	@GetMapping("/findRouteView")
	public String findRouteView(HttpServletRequest request, Model model) {
		System.out.println("경로 검색 페이지 시작");
		return "route/findRouteView";
	}

	@GetMapping("/registRoute")
	public String registRoute(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("경로 등록 시작");
		String msg = "";
		try{
			Map<String, String[]> parameterMap = request.getParameterMap();
			
			PlanDto planDto = new PlanDto();
			planDto.setContent(parameterMap.get("content")[0]);
			planDto.setTitle(parameterMap.get("title")[0]);
			planDto.setExpectedDistance(parameterMap.get("expectedDistance")[0]);
			planDto.setExpectedTime(parameterMap.get("expectedTime")[0]);
			planDto.setTaxiCost(Integer.valueOf(parameterMap.get("taxiCost")[0]));
			planDto.setFuelCost(Integer.valueOf(parameterMap.get("fuelCost")[0]));
			planDto.setUserId(((UserDto) session.getAttribute("userInfo")).getId());
//			planDto.setUserId("test");

			List<TourDto> tourList = new ArrayList<>();

			for (int i = 0; i < parameterMap.get("tourTitle").length; i++) {
				TourDto tourDto = new TourDto();
				tourDto.setLatitude(Float.parseFloat(parameterMap.get("tourLatitude")[i]));
				tourDto.setLongitude(Float.parseFloat(parameterMap.get("tourlongitude")[i]));
				tourDto.setTitle(parameterMap.get("tourTitle")[i]);
				tourDto.setAddress(parameterMap.get("tourAddress")[i]);
				tourDto.setTelephone(parameterMap.get("tourTel")[i]);
				tourDto.setDistance(Integer.valueOf(parameterMap.get("tourDistance")[i]));
				tourDto.setTime(Integer.valueOf(parameterMap.get("tourTime")[i]));
				tourList.add(tourDto);
			}
			planService.insertRoute(planDto, tourList);
			msg="여행 계획 등록을 성공했습니다.";
		}catch(Exception e) {
			e.printStackTrace();
			msg="여행 계획 등록을 실패했습니다.";
		}
		model.addAttribute("msg",msg);
		
		return "route/findRouteView";
	}

	@GetMapping("/registRouteView")
	public String registRouteView(HttpServletRequest request, Model model) {
		System.out.println("경로 상세 페이지 시작");
		String titleArr[] = request.getParameterValues("title");
		String latitudeArr[] = request.getParameterValues("latitude");
		String longitudeArr[] = request.getParameterValues("longitude");
		String addressArr[] = request.getParameterValues("address");
		String telArr[] = request.getParameterValues("tel");

		List<TourDto> tourList = new ArrayList<>();

		for (int i = 0; i < titleArr.length; i++) {
			TourDto tour = new TourDto();

			tour.setTitle(titleArr[i]);
			tour.setLatitude(Float.parseFloat(latitudeArr[i]));
			tour.setLongitude(Float.parseFloat(longitudeArr[i]));
			tour.setAddress(addressArr[i]);
			tour.setTelephone(telArr[i]);
			System.out.println(tour);
			tourList.add(tour);
		}

		model.addAttribute("tourList", tourList);

		return "route/registRouteView";
	}

	@GetMapping("/findRoute")
	@ResponseBody
	public ResponseEntity<?> findRoute(HttpServletRequest request, Model model) {
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
