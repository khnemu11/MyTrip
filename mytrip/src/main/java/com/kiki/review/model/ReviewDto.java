package com.kiki.review.model;

public class ReviewDto {
	private int seq;
	private String title;
	private String content;
	private String userId;
	private String createdDate;
	private String modifedDate;
	private String deletedDate;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifedDate() {
		return modifedDate;
	}
	public void setModifedDate(String modifedDate) {
		this.modifedDate = modifedDate;
	}
	public String getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(String deletedDate) {
		this.deletedDate = deletedDate;
	}
	@Override
	public String toString() {
		return "ReviewDto [seq=" + seq + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", createdDate=" + createdDate + ", modifedDate=" + modifedDate + ", deletedDate=" + deletedDate
				+ "]";
	}
	
}
