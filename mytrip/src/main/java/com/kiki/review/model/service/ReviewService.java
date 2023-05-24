package com.kiki.review.model.service;

import java.util.List;

import com.kiki.review.model.ReviewDto;
import com.kiki.route.model.SearchDto;

public interface ReviewService {

	int writeReview(ReviewDto reviewForm);

	List<ReviewDto> getList();

	List<ReviewDto> searchReview(SearchDto search);


}
