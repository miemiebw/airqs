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
	private String name;
	private String areaName;
	private Integer cityId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Station [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append(", cityId=");
		builder.append(cityId);
		builder.append("]");
		return builder.toString();
	}
	
}
