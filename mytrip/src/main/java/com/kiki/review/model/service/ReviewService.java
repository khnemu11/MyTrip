package com.kiki.review.model.service;

import java.util.List;

import com.kiki.review.model.ReviewDto;

public interface ReviewService {

	int writeReview(ReviewDto reviewForm);

	List<ReviewDto> getList();

}
