/**
 * 
 */
package com.github.airqs.entity;

/**
 * @author Eric
 *
 */
public class City extends BaseEntity{
	private Integer id;
	private String name;
	private String provinceName;
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
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("City [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", provinceName=");
		builder.append(provinceName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
