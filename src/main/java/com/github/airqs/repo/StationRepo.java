/**
 * 
 */
package com.github.airqs.repo;

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
	
	public Station getByProvinceNameAndCityNameAndPointName(String provinceName,String cityName,String pointName){
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("provinceName", provinceName);
		parameter.put("cityName", cityName);
		parameter.put("pointName", pointName);
		return (Station) sqlSession.selectOne("airqs.station.getByProvinceNameAndCityNameAndPointName", parameter);
	}
	
	public void add(Station station){
		sqlSession.insert("airqs.station.add", station);
	}
}
