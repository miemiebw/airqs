/**
 * 
 */
package com.github.airqs.crawl;

import java.util.Date;

/**
 * @author Eric
 *
 */
public class AirCityHour {
	private String provinceName;
	private String cityName;
	private Float so2;
	private Float no2;
	private Float pm10;
	private Date reportTime;
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AirCityHour [provinceName=");
		builder.append(provinceName);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", so2=");
		builder.append(so2);
		builder.append(", no2=");
		builder.append(no2);
		builder.append(", pm10=");
		builder.append(pm10);
		builder.append(", reportTime=");
		builder.append(reportTime);
		builder.append("]");
		return builder.toString();
	}
	
}
