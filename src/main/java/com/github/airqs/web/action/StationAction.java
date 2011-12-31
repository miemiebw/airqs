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
package com.github.airqs.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.github.airqs.entity.Station;
import com.github.airqs.entity.StationHour;
import com.github.airqs.manager.AirManager;
import com.github.glue.mvc.Reply;
import com.github.glue.mvc.annotation.Action;
import com.github.glue.mvc.annotation.Get;
import com.github.glue.mvc.annotation.Param;
import com.github.glue.mvc.annotation.Path;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Eric.Lee
 *
 */
@Action
public class StationAction {
	
	private AirManager airManager;
	
	@Get
	@Path("/searchStationNow.json")
	public Reply searchStationNow(@Param("lat")Double lat, @Param("lng")Double lng){
		Station station = airManager.getStationByLatLng(lat, lng);
		StationHour stationHour = airManager.getLastStationHourByStationId(station.getId());
		
		StationReport report = convert(station, stationHour);
		
		return Reply.asJson().with(report);
	}
	
	@Get
	@Path("/searchStationById.json")
	public Reply searchStationById(@Param("stationId")Integer staionId){
		Station station = airManager.getStation(staionId);
		StationHour stationHour = airManager.getLastStationHourByStationId(station.getId());
		StationReport report = convert(station, stationHour);
		
		return Reply.asJson().with(report);
	}

	@Get
	@Path("/searchStation.json")
	public Reply searchStation(@Param("cityName")String cityName){
		
		List<StationGroup> stationGroups = Lists.newArrayList();
		if(!Strings.isNullOrEmpty(cityName)){
			List<Station> stations = airManager.findStationLikeCityName(cityName);
			Map<String, List<StationReport>> groups = Maps.newHashMap();
			for (Station station : stations) {
				StationHour stationHour = airManager.getLastStationHourByStationId(station.getId());
				if(stationHour != null){
					String name = station.getProvinceName() +" － " +station.getCityName();
					StationReport report = convert(station, stationHour);
					List<StationReport> reports = groups.get(name);
					if(reports == null){
						reports = Lists.newArrayList();
						groups.put(name, reports);
					}
					reports.add(report);
				}
			}
			
			for(Map.Entry<String, List<StationReport>> entry : groups.entrySet()){
				StationGroup group = new StationGroup();
				group.setCityName(entry.getKey());
				group.setReports(entry.getValue());
				stationGroups.add(group);
			}
		}
		return Reply.asJson().with(stationGroups);
	}
	
	
	private StationReport convert(Station station,StationHour stationHour){
		StationReport report = new StationReport();
		report.setStationId(station.getId());
		report.setProvinceName(station.getProvinceName());
		report.setCityName(station.getCityName());
		report.setPointName(station.getPointName());
		
		report.setPm10(stationHour.getPm10());
		report.setSo2(stationHour.getSo2());
		report.setNo2(stationHour.getNo2());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date reportTime = stationHour.getCollectTime();
		if(reportTime != null){
			report.setReportTime(format.format(reportTime));
		}else{
			report.setReportTime("");
		}
		return report;
	}
	
	@Inject
	public void setAirManager(AirManager airManager) {
		this.airManager = airManager;
	}
	
	public class StationGroup{
		private String cityName;
		private List<StationReport> reports;
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public List<StationReport> getReports() {
			return reports;
		}
		public void setReports(List<StationReport> reports) {
			this.reports = reports;
		}
		
		
		
		
	}
	
	public class StationReport{
		private Integer stationId;
		private String provinceName;
		private String cityName;
		private String pointName;
		private Float pm10;
		private Float so2;
		private Float no2;
		private String reportTime;
		
		public Integer getStationId() {
			return stationId;
		}
		public void setStationId(Integer stationId) {
			this.stationId = stationId;
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
		public Float getPm10() {
			return pm10;
		}
		public void setPm10(Float pm10) {
			this.pm10 = pm10;
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
		public String getReportTime() {
			return reportTime;
		}
		public void setReportTime(String reportTime) {
			this.reportTime = reportTime;
		}
		
		
		
	}
	
	
}
