package com.kiki.route.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlanDto {
	private int seq;
	private String userId;
	private String title;
	private String content;
	private String expectedTime;
	private String expectedDistance;
	private int taxiCost;
	private int fuelCost;
	private String createdTime;

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		LocalDateTime dateTime = createdTime.toLocalDateTime();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.createdTime = dateTime.format(format);
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}

	public String getExpectedDistance() {
		return expectedDistance;
	}

	public void setExpectedDistance(String expectedDistance) {
		this.expectedDistance = expectedDistance;
	}

	public int getTaxiCost() {
		return taxiCost;
	}

	public void setTaxiCost(int taxiCost) {
		this.taxiCost = taxiCost;
	}

	public int getFuelCost() {
		return fuelCost;
	}

	public void setFuelCost(int fuelCost) {
		this.fuelCost = fuelCost;
	}

	@Override
	public String toString() {
		return "PlanService [seq=" + seq + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", expectedTime=" + expectedTime + ", expectedDistance=" + expectedDistance + ", taxiCost=" + taxiCost
				+ ", fuelCost=" + fuelCost + "]";
	}
}