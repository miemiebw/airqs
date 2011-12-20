package com.github.airqs.manager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.airqs.entity.City;
import com.github.airqs.entity.CityHour;
import com.github.airqs.entity.Station;
import com.github.airqs.entity.StationHour;
import com.github.airqs.repo.CityHourRepo;
import com.github.airqs.repo.CityRepo;
import com.github.airqs.repo.StationHourRepo;
import com.github.airqs.repo.StationRepo;

@Singleton
public class AirManager {
	private Logger log = LoggerFactory.getLogger(AirManager.class);
	@Inject
	CityRepo cityRepo;
	@Inject
	StationRepo stationRepo;
	@Inject
	CityHourRepo cityHourRepo;
	@Inject
	StationHourRepo stationHourRepo;
	
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
	
	public Station getStation(Integer id){
		return stationRepo.get(id);
	}
	
	public Station getStationByProvinceNameAndCityNameAndPointName(String provinceName,String cityName,String pointName){
		return stationRepo.getByProvinceNameAndCityNameAndPointName(provinceName, cityName, pointName);
	}
	
	public List<Station> findByProvinceNameAndCityName(String provinceName,String cityName){
		return stationRepo.findByProvinceNameAndCityName(provinceName, cityName);
	}
	
	public void addStation(Station station){
		stationRepo.add(station);
	}
	
	public Station getStationByLatLng(Double lat,Double lng){
		List<Station> stations = stationRepo.findAll();
		Station result = null;
		for (Station station : stations) {
			if(result == null){
				result = station;
				continue;
			}
			double newDist = MapUtils.getDistance(station.getLat(), station.getLng(), lat, lng);
			log.debug("{} - {}",station, newDist);
			if(MapUtils.getDistance(result.getLat(), result.getLng(), lat, lng) > newDist){
				result = station;
			}
		}
		return result;
	}
	
	
	public StationHour getLastStationHourByStationId(Integer stationId){
		return stationHourRepo.getLastReportByStationId(stationId);
	}
	
	public void addStationHour(StationHour stationHour){
		stationHourRepo.add(stationHour);
	}
	
	public void updateStationHour(StationHour stationHour){
		stationHourRepo.update(stationHour);
	}
}
