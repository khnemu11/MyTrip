package com.kiki.review.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.review.model.ReviewDto;

@Mapper
public interface ReviewDao {

	public int writeReview(ReviewDto reivewForm);

	public List<ReviewDto> getList();

	public List<ReviewDto> searchReview(String keyword);

}
