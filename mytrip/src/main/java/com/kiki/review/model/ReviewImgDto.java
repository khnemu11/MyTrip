package com.kiki.review.model;

public class ReviewImgDto {
	private int seq;
	private int reviewSeq;
	private String imageName;
	private String imageCode;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getReviewSeq() {
		return reviewSeq;
	}
	public void setReviewSeq(int reviewSeq) {
		this.reviewSeq = reviewSeq;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	@Override
	public String toString() {
		return "ReviewImgDto [seq=" + seq + ", reviewSeq=" + reviewSeq + ", imageName=" + imageName + ", imageCode="
				+ imageCode + "]";
	}
	
}
