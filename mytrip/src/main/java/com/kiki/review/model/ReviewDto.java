package com.kiki.review.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReviewDto {
	private int seq;
	private String title;
	private String content;
	private String userId;
	private String userName;
	private String createdDate;
	private String modifiedDate;
	private String deletedDate;
	private String imageCode; // 대표 이미지 하나
	private String tourTitle;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		LocalDateTime dateTime = createdDate.toLocalDateTime();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// 만약 오늘이면 시간나오게 처리
		this.createdDate = dateTime.format(format);
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		LocalDateTime dateTime = modifiedDate.toLocalDateTime();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// 만약 오늘이면 시간나오게 처리
		this.modifiedDate = dateTime.format(format);
	}
	public String getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(String deletedDate) {
		this.deletedDate = deletedDate;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public String getTourTitle() {
		return tourTitle;
	}
	public void setTourTitle(String tourTitle) {
		this.tourTitle = tourTitle;
	}
	@Override
	public String toString() {
		return "ReviewDto [seq=" + seq + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", userName=" + userName + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", deletedDate=" + deletedDate + ", imageCode=" + imageCode + ", tourTitle=" + tourTitle + "]";
	}


}
