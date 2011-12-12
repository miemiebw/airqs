package com.github.airqs.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.airqs.entity.City;
import com.github.airqs.entity.CityHour;
import com.github.airqs.repo.CityHourRepo;
import com.github.airqs.repo.CityRepo;
import com.github.airqs.repo.StationRepo;

@Singleton
public class AirManager {

	@Inject
	CityRepo cityRepo;
	@Inject
	StationRepo stationRepo;
	@Inject
	CityHourRepo cityHourRepo;
	
	public City getCityByProvinceNameAndName(String provinceName,String name){
		return cityRepo.getByProvinceNameAndName(provinceName, name);
	}
	
	public void addCity(City city){
		cityRepo.add(city);
	}
	
	public CityHour getLastCityHourByCityId(Integer cityId){
		return cityHourRepo.getLastReportByCityId(cityId);
	}
	
	public void addCityHour(CityHour cityHour){
		cityHourRepo.add(cityHour);
	}
	public void updateCityHour(CityHour cityHour){
		cityHourRepo.update(cityHour);
	}
}
