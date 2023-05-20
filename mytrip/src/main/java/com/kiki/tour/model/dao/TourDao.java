package com.kiki.tour.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.tour.model.TourDto;

@Mapper
public interface TourDao {
	public TourDto selectTour(TourDto tourDto) throws Exception;

	public int insertTour(TourDto tourDto) throws Exception;

	public int updateTourHit(TourDto tourDto) throws Exception;
}
