package com.kiki.email.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmailDto{
	private int code;
	LocalDateTime publishedTime;
	int expiredTime = 30;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public LocalDateTime getPublishedTime() {
		return publishedTime;
	}
	public void setPublishedTime(LocalDateTime publishedTime) {
		this.publishedTime = publishedTime;
	}
	public int getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(int expiredTime) {
		this.expiredTime = expiredTime;
	}
	@Override
	public String toString() {
		return "EmailDto [code=" + code + ", publishedTime=" + publishedTime + ", expiredTime=" + expiredTime + "]";
	} 
	
	
}