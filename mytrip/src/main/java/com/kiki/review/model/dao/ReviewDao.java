package com.kiki.review.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.review.model.ReviewDto;
import com.kiki.review.model.ReviewImgDto;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;

@Mapper
public interface ReviewDao {

	public int writeReview(ReviewDto reivewForm);
	
	public int getLastestReview(String id);

	public List<ReviewDto> getList();

	public List<ReviewDto> searchReview(SearchDto search);
	
	public ReviewDto getReviewDetail(int seq);

	public List<ReviewImgDto> getReviewImg(int seq);

	public int deleteReview(int seq);
	
	public int insertImage(ReviewImgDto imgDto);
	
	public List<ReviewDto> selectReviewList(SearchDto searchDto);
	
	public Integer countReviewList(SearchDto searchDto);

	public int updateReview(ReviewDto reviewForm);
	
}
