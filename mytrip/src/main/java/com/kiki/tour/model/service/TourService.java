package com.kiki.tour.model.service;

import java.util.List;

import com.kiki.tour.model.TourDto;

public interface TourService {
	public TourDto selectTour(TourDto tourDto) throws Exception;

	public int insertTour(TourDto tourDto) throws Exception;

	public int updateTourHit(TourDto tourDto) throws Exception;

	public TourDto selectTourByTitle(TourDto tourDto) throws Exception;
	
	public List<TourDto> selectHotTourList() throws Exception;
}
