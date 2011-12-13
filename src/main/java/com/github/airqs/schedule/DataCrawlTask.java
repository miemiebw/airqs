/**
 * 
 */
package com.github.airqs.schedule;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.airqs.crawl.AirCityHour;
import com.github.airqs.crawl.AirFinder;
import com.github.airqs.crawl.AirStationHour;
import com.github.airqs.entity.City;
import com.github.airqs.entity.CityHour;
import com.github.airqs.entity.Station;
import com.github.airqs.entity.StationHour;
import com.github.airqs.manager.AirManager;
import com.google.common.base.Equivalences;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author Eric
 *
 */
@Singleton
public class DataCrawlTask implements Runnable {
	private Logger log = LoggerFactory.getLogger(DataCrawlTask.class);
	@Inject
	AirFinder airFinder;
	@Inject
	AirManager airManager;
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try{
		Set<String> pros = Sets.newLinkedHashSet();
		List<AirCityHour> cityHours = airFinder.findCityHour();
		for (AirCityHour airCityHour : cityHours) {
			City city = airManager.getCityByProvinceNameAndName(airCityHour.getProvinceName(), airCityHour.getCityName());
			if(city == null){
				city = new City();
				city.setName(airCityHour.getCityName());
				city.setProvinceName(airCityHour.getProvinceName());
				airManager.addCity(city);
			}
			
			CityHour lastReport = airManager.getLastCityHourByCityId(city.getId());
			if(lastReport == null || lastReport.getReportTime().before(airCityHour.getReportTime())){
				CityHour cityHour = new CityHour();
				cityHour.setCityId(city.getId());
				cityHour.setReportTime(airCityHour.getReportTime());
				cityHour.setSo2(airCityHour.getSo2());
				cityHour.setNo2(airCityHour.getNo2());
				cityHour.setPm10(airCityHour.getPm10());
				cityHour.setCollectTime(new Date());
				airManager.addCityHour(cityHour);
			}else if(lastReport.getReportTime().equals(airCityHour.getReportTime())){
				if(!Equivalences.equals().equivalent(lastReport.getSo2(), airCityHour.getSo2()) ||
						!Equivalences.equals().equivalent(lastReport.getNo2(), airCityHour.getNo2()) ||
						!Equivalences.equals().equivalent(lastReport.getPm10(), airCityHour.getPm10())){
					lastReport.setSo2(airCityHour.getSo2());
					lastReport.setNo2(airCityHour.getNo2());
					lastReport.setPm10(airCityHour.getPm10());
					lastReport.setCollectTime(new Date());
					airManager.updateCityHour(lastReport);
				}
			}
			pros.add(city.getProvinceName());
			
		}
		
		for (String pro : pros) {
			List<AirStationHour> stationHours = airFinder.findStationHour(pro);
			for (AirStationHour airStationHour : stationHours) {
				Station station = airManager.getStationByProvinceNameAndCityNameAndPointName(airStationHour.getProvinceName(), airStationHour.getCityName(), airStationHour.getPointName());
				if(station == null){
					station = new Station();
					station.setProvinceName(airStationHour.getProvinceName());
					station.setCityName(airStationHour.getCityName());
					station.setPointName(airStationHour.getPointName());
					station.setAddress(airStationHour.getAddress());
					station.setLat(airStationHour.getLat());
					station.setLng(airStationHour.getLng());
					station.setHeight(airStationHour.getHeight());
					airManager.addStation(station);
				}
				
				StationHour stationHour = airManager.getLastStationHourByStationId(station.getId());
				
				if(stationHour == null || stationHour.getReportTime().before(airStationHour.getReportTime())){
					stationHour = new StationHour();
					stationHour.setStationId(station.getId());
					stationHour.setReportTime(airStationHour.getReportTime());
					stationHour.setSo2(airStationHour.getSo2());
					stationHour.setNo2(airStationHour.getNo2());
					stationHour.setPm10(airStationHour.getPm10());
					stationHour.setCollectTime(new Date());
					airManager.addStationHour(stationHour);
				}else if(stationHour.getReportTime().equals(airStationHour.getReportTime())){
					if(!Equivalences.equals().equivalent(stationHour.getSo2(), airStationHour.getSo2()) ||
							!Equivalences.equals().equivalent(stationHour.getNo2(), airStationHour.getNo2()) ||
							!Equivalences.equals().equivalent(stationHour.getPm10(), airStationHour.getPm10())){
						stationHour.setSo2(airStationHour.getSo2());
						stationHour.setNo2(airStationHour.getNo2());
						stationHour.setPm10(airStationHour.getPm10());
						stationHour.setCollectTime(new Date());
						airManager.updateStationHour(stationHour);
					}
				}
				
			}
		}
		
		
		
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

}
