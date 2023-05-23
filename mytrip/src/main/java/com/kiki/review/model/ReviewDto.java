package com.kiki.review.model;

import java.util.List;

public class ReviewDto {
	private int seq;
	private String title;
	private String content;
	private String userName;
	private String createdDate;
	private String modifiedDate;
	private String deletedDate;
	private String imageCode;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	@Override
	public String toString() {
		return "ReviewDto [seq=" + seq + ", title=" + title + ", content=" + content + ", userName=" + userName
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", deletedDate=" + deletedDate
				+ ", imageCode=" + imageCode + "]";
	}

}
