package com.kiki.tour.model;

public class TourYoutubeDto {
	private String videoId;
	private String imgSrc;

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	@Override
	public String toString() {
		return "TourYoutubeDto [videoId=" + videoId + ", imgSrc=" + imgSrc + "]";
	}

}
