/**
 * 
 */
package com.github.airqs.schedule;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.airqs.crawl.AirCityHour;
import com.github.airqs.crawl.AirFinder;
import com.github.airqs.entity.City;
import com.github.airqs.entity.CityHour;
import com.github.airqs.manager.AirManager;
import com.google.common.base.Equivalences;

/**
 * @author Eric
 *
 */
@Singleton
public class DataCrawlTask implements Runnable {
	
	@Inject
	AirFinder airFinder;
	@Inject
	AirManager airManager;
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
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
			
			
			
		}
	}

}
