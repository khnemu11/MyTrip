package com.kiki.route.model.service;

import java.util.List;

import com.kiki.route.model.PlanDto;
import com.kiki.route.model.PlanOrderDto;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;

public interface PlanService{
	public int insertRoute(PlanDto planDto,List<TourDto>tourList);
	public List<PlanDto> selectPlanList(SearchDto searchDto);
	public Integer countPlanList(SearchDto searchDto);
	public PlanDto selectPlan(int seq);
	public List<PlanOrderDto> selectPlanOrderList(int seq);
}