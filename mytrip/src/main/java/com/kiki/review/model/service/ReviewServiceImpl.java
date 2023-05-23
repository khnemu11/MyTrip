package com.kiki.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.dao.ReviewDao;

@Service
public class ReviewServiceImpl implements ReviewService {

	ReviewDao reviewDao;
	
	@Autowired
	public ReviewServiceImpl(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	@Override
	public int writeReview(ReviewDto reviewForm) {
		System.out.println(reviewForm);
		return reviewDao.writeReview(reviewForm);
	}

	@Override
	public List<ReviewDto> getList() {
		return reviewDao.getList();
	}

	@Override
	public List<ReviewDto> searchReview(String keyword) {
		return reviewDao.searchReview(keyword);
	}

}
