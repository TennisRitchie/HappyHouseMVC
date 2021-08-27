package com.ssafy.happyhouse.model;

public class HouseDealDto {
	String dong;
	String AptName;
	String dealAmount;
	String buildYear;
	String area;
	String type;
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getAptName() {
		return AptName;
	}
	public void setAptName(String aptName) {
		AptName = aptName;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public HouseDealDto(String dong, String aptName, String dealAmount, String buildYear, String area, String type) {
		super();
		this.dong = dong;
		AptName = aptName;
		this.dealAmount = dealAmount;
		this.buildYear = buildYear;
		this.area = area;
		this.type = type;
	}
	public HouseDealDto() {
		super();
	}
	
	
}
