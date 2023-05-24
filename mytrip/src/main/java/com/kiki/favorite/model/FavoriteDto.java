package com.kiki.favorite.model;

public class FavoriteDto {
	private int seq;
	private int tourSeq;
	private String userId;
	private String title;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getTourSeq() {
		return tourSeq;
	}

	public void setTourSeq(int tourSeq) {
		this.tourSeq = tourSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "FavoriteDto [seq=" + seq + ", tourSeq=" + tourSeq + ", userId=" + userId + ", title=" + title + "]";
	}

}
