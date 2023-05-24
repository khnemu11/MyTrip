package com.kiki.review.model.service;

import java.util.List;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.ReviewImgDto;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;

public interface ReviewService {

	int writeReview(ReviewDto reviewForm,TourDto tourDto) throws Exception;

	int getLastestReview(String id);
	
	List<ReviewDto> getList();

	List<ReviewDto> searchReview(SearchDto search);

	ReviewDto getReviewDetail(int seq);

	List<ReviewImgDto> getReviewImg(int seq);
}
