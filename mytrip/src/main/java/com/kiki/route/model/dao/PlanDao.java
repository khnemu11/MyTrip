package com.kiki.route.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.route.model.PlanDto;
import com.kiki.route.model.PlanOrderDto;

@Mapper
public interface PlanDao{
	public int insertPlan(PlanDto planDto);
	public int selectPlanSeq(PlanDto planDto);
	public int insertPlanOrder(PlanOrderDto planOrderDto);
}