package com.kiki.route.model.service;

import java.util.List;

import com.kiki.route.model.PlanDto;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;

public interface PlanService{
	public int insertRoute(PlanDto planDto,List<TourDto>tourList);
	public List<PlanDto> selectPlanList(SearchDto searchDto);
	public int countPlanList(SearchDto searchDto);
}