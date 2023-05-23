package com.kiki.route.model.service;

import java.util.List;

import com.kiki.route.model.PlanDto;
import com.kiki.tour.model.TourDto;

public interface PlanService{
	public int insertRoute(PlanDto planDto,List<TourDto>tourList);
}