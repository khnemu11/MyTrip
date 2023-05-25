package com.kiki.route.model;

import org.springframework.beans.factory.annotation.Value;

public class SearchDto {
	private String keyword;
	private int totalCount;
	@Value("1")
	private int pageNo;
	private String userId;
	@Value("10")
	private int pageSize;
	private int offset;
	private boolean before;
	private int start;
	private int end;
	private boolean next;

	public boolean isBefore() {
		return before;
	}
	public void setBefore(boolean before) {
		this.before = before;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "SearchDto [keyword=" + keyword + ", totalCount=" + totalCount + ", pageNo=" + pageNo + ", userId="
				+ userId + ", pageSize=" + pageSize + ", offset=" + offset + ", before=" + before + ", start=" + start
				+ ", end=" + end + ", next=" + next + "]";
	}

}
