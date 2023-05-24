package com.kiki.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.ReviewImgDto;
import com.kiki.review.model.dao.ReviewDao;
import com.kiki.route.model.SearchDto;

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

}
