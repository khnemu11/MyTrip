package com.kiki.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.ReviewImgDto;
import com.kiki.review.model.dao.ReviewDao;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.dao.TourDao;

@Service
public class ReviewServiceImpl implements ReviewService {

	ReviewDao reviewDao;
	TourDao tourDao;
	
	@Autowired
	public ReviewServiceImpl(ReviewDao reviewDao,TourDao tourDao) {
		this.reviewDao = reviewDao;
		this.tourDao =  tourDao;
	}
	
	@Override
	@Transactional
	public int writeReview(ReviewDto reviewForm,TourDto tourDto) throws Exception{
		System.out.println(reviewForm);
		tourDao.updateTourHit(tourDto);
		return reviewDao.writeReview(reviewForm);
	}

	@Override
	public int getLastestReview(String id) {
		return reviewDao.getLastestReview(id);
	}
	
	@Override
	public List<ReviewDto> getList() {
		return reviewDao.getList();
	}

	@Override
	public List<ReviewDto> searchReview(SearchDto search) {
		return reviewDao.searchReview(search);
	}

	@Override
	public ReviewDto getReviewDetail(int seq) {
		return reviewDao.getReviewDetail(seq);
	}

	@Override
	public List<ReviewImgDto> getReviewImg(int seq) {
		return reviewDao.getReviewImg(seq);
	}

	@Override
	public int deleteReview(int seq) {
		return reviewDao.deleteReview(seq);
	}

}
