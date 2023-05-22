package com.kiki.review.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.review.model.ReviewDto;

@Mapper
public interface ReviewDao {

	public int writeReview(ReviewDto reivewForm);

}
