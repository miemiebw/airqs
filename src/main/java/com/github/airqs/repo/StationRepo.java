/**
 * 
 */
package com.github.airqs.repo;

import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import com.github.airqs.base.BaseMybatisDao;
import com.github.airqs.entity.Station;
import com.google.common.collect.Maps;

/**
 * @author Eric
 *
 */
@Singleton
public class StationRepo extends BaseMybatisDao {
	
	public Station get(Integer id){
		return (Station) sqlSession.selectOne("airqs.station.get", id);
	}
	
	public Station getByProvinceNameAndCityNameAndPointName(String provinceName,String cityName,String pointName){
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("provinceName", provinceName);
		parameter.put("cityName", cityName);
		parameter.put("pointName", pointName);
		return (Station) sqlSession.selectOne("airqs.station.getByProvinceNameAndCityNameAndPointName", parameter);
	}
	
	public List<Station> findByProvinceNameAndCityName(String provinceName,String cityName){
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("provinceName", provinceName);
		parameter.put("cityName", cityName);
		return sqlSession.selectList("airqs.station.findByProvinceNameAndCityName", parameter);
	}
	
	public void add(Station station){
		sqlSession.insert("airqs.station.add", station);
	}
}
