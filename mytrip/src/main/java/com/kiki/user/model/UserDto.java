package com.kiki.user.model;

import java.sql.Timestamp;

public class UserDto {
	private int seq;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phoneNo;
	private String intro;
	private Timestamp joinDate;
	private Timestamp modifiedDate;
	private Timestamp withdrawalDate;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Timestamp getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
//		joinDate.toLocalDateTime();
		this.joinDate = joinDate;
		
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Timestamp getWithdrawalDate() {
		return withdrawalDate;
	}
	public void setWithdrawalDate(Timestamp withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}
	
	@Override
	public String toString() {
		return "UserDto [seq=" + seq + ", id=" + id + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phoneNo=" + phoneNo + ", intro=" + intro + ", joinDate=" + joinDate + ", modifiedDate="
				+ modifiedDate + ", withdrawalDate=" + withdrawalDate + "]";
	}
}
