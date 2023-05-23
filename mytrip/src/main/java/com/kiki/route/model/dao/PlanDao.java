package com.kiki.route.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.route.model.PlanDto;
import com.kiki.route.model.PlanOrderDto;
import com.kiki.route.model.SearchDto;

@Mapper
public interface PlanDao{
	public int insertPlan(PlanDto planDto);
	public int selectPlanSeq(PlanDto planDto);
	public int insertPlanOrder(PlanOrderDto planOrderDto);
	public List<PlanDto> selectPlanList(SearchDto searchDto);
	public int countPlanList(SearchDto searchDto);
	PlanDto selectPlan(int seq);
	public List<PlanOrderDto> selectPlanOrderList(int seq);
}