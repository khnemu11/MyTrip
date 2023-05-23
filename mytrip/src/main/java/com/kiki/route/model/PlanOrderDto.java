package com.kiki.route.model;

import com.kiki.tour.model.TourDto;

public class PlanOrderDto{
	private int seq;
	private int planSeq;
	private int order;
	private int tourSeq;
	private int distance;
	private int time;
	private TourDto tourDto;
	
	public TourDto getTourDto() {
		return tourDto;
	}
	public void setTourDto(TourDto tourDto) {
		this.tourDto = tourDto;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getTourSeq() {
		return tourSeq;
	}
	public void setTourSeq(int tourSeq) {
		this.tourSeq = tourSeq;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getPlanSeq() {
		return planSeq;
	}
	public void setPlanSeq(int planSeq) {
		this.planSeq = planSeq;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "PlanOrderDto [seq=" + seq + ", planSeq=" + planSeq + ", order=" + order + ", tourSeq=" + tourSeq
				+ ", distance=" + distance + ", time=" + time + ", tourDto=" + tourDto + "]";
	}
}