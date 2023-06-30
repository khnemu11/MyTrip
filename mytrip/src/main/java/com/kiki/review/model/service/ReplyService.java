package com.kiki.review.model.service;

import java.util.List;

import com.kiki.review.model.ReplyDto;

public interface ReplyService {
	public int writeReply(ReplyDto replyDto);
	public int editReply(ReplyDto replyDto);
	public int deleteReply(ReplyDto replyDto);
	public List<ReplyDto> getReplyList(int reviewNo);
}