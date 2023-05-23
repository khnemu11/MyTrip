package com.kiki.route.model;

import org.springframework.beans.factory.annotation.Value;

public class SearchDto {
	private String keyword;
	private int totalCount;
	private int pageNo;
	private String userId;
	private int pageSize = 10;
	private int offset;

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

	@Override
	public String toString() {
		return "SearchDto [keyword=" + keyword + ", totalCount=" + totalCount + ", pageNo=" + pageNo + ", userId="
				+ userId + ", pageSize=" + pageSize + ", offset=" + offset + "]";
	}


}
