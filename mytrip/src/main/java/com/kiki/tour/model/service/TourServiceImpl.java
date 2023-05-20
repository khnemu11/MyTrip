package com.kiki.tour.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.dao.TourDao;

@Service
public class TourServiceImpl implements TourService {
	TourDao tourDao;

	@Autowired
	public TourServiceImpl(TourDao tourDao) {
		this.tourDao = tourDao;
	}

	@Override
	@Transactional
	public TourDto selectTour(TourDto tourDto) throws Exception {
		tourDao.updateTourHit(tourDto);
		System.out.println(tourDto);
		return tourDao.selectTour(tourDto);
	}

	@Override
	public int insertTour(TourDto tourDto) throws Exception {
		return tourDao.insertTour(tourDto);
	}

	@Override
	public int updateTourHit(TourDto tourDto) throws Exception {
		System.out.println(tourDto);
		return tourDao.updateTourHit(tourDto);
	}
}
