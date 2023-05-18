package com.kiki.user.model;

public class UserDto {
	private String id;
	private int salary;
	private String name;
	private String designation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", salary=" + salary + ", name=" + name + ", designation=" + designation + "]";
	}





}
