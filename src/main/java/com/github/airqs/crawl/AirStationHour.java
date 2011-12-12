/*
 * Copyright Eric Lee.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.airqs.crawl;

/**
 * @author Eric.Lee
 *
 */
public class AirStationHour extends AirCityHour{
	private String pointName;
	private String address;
	private Double lat;
	private Double lng;
	private Double height;

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
		builder.append("AirStationHour [pointName=");
		builder.append(pointName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", height=");
		builder.append(height);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


	
	
}
