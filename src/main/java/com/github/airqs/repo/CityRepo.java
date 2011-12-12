/**
 * 
 */
package com.github.airqs.repo;


import java.util.Map;

import javax.inject.Singleton;

import com.github.airqs.base.BaseMybatisDao;
import com.github.airqs.entity.City;
import com.google.common.collect.Maps;

/**
 * @author Eric
 *
 */
@Singleton
public class CityRepo extends BaseMybatisDao {
	
	public void add(City city){
		sqlSession.insert("airqs.city.add", city);
	}
	
	public City getByProvinceNameAndName(String provinceName,String name){
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("provinceName", provinceName);
		parameter.put("name", name);
		return (City) sqlSession.selectOne("airqs.city.getByProvinceNameAndName", parameter);
	}
	
}
