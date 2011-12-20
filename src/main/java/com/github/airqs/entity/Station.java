/**
 * 
 */
package com.github.airqs.entity;

/**
 * @author Eric
 *
 */
public class Station extends BaseEntity {
	private Integer id;
	private String provinceName;
	private String cityName;
	private String pointName;
	private String address;
	private Double lat;
	private Double lng;
	private Double height;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Station [id=");
		builder.append(id);
		builder.append(", provinceName=");
		builder.append(provinceName);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", pointName=");
		builder.append(pointName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", height=");
		builder.append(height);
		builder.append("]");
		return builder.toString();
	}
	

	
}
