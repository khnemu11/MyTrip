package com.kiki.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.review.model.ReplyDto;
import com.kiki.review.model.dao.ReplyDao;

@Service
public class ReplyServiceImpl implements ReplyService{
	ReplyDao replyDao;
	
	@Autowired
	public ReplyServiceImpl(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	@Override
	public int writeReply(ReplyDto replyDto) {
		return replyDao.insertReply(replyDto);
	}

	@Override
	public int editReply(ReplyDto replyDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReply(ReplyDto replyDto) {
		return replyDao.deleteReply(replyDto);
	}

	@Override
	public List<ReplyDto> getReplyList(int reviewNo) {
		return replyDao.selectReplyList(reviewNo);
	}
}
