/**
 * 
 */
package com.github.airqs.entity;

import java.util.Date;

/**
 * @author Eric
 *
 */
public class CityHour extends BaseEntity {
	private Long id;
	private Integer cityId;
	private Date reportTime;
	private Float so2;
	private Float no2;
	private Float pm10;
	private Date collectTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public Float getSo2() {
		return so2;
	}
	public void setSo2(Float so2) {
		this.so2 = so2;
	}
	public Float getNo2() {
		return no2;
	}
	public void setNo2(Float no2) {
		this.no2 = no2;
	}
	public Float getPm10() {
		return pm10;
	}
	public void setPm10(Float pm10) {
		this.pm10 = pm10;
	}
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	
	
}
