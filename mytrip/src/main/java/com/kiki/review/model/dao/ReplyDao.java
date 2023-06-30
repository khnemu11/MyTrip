package com.kiki.review.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.review.model.ReplyDto;

@Mapper
public interface ReplyDao {
	public int insertReply(ReplyDto replyDto);
	public int updateReply(ReplyDto replyDto);
	public int deleteReply(ReplyDto replyDto);
	public List<ReplyDto> selectReplyList(int reviewNo);
}
