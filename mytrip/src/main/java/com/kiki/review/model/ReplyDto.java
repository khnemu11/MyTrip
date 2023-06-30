package com.kiki.review.model;

public class ReplyDto {
	private int seq;
	private String ctime;
	private String utime;
	private String context;
	private int parentSeq;
	private String writer;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getUtime() {
		return utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "ReplyDto [seq=" + seq + ", ctime=" + ctime + ", utime=" + utime + ", context=" + context
				+ ", parentSeq=" + parentSeq + ", writer=" + writer + "]";
	}
}
