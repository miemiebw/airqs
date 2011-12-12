/**
 * 
 */
package com.github.airqs.repo;


import java.util.List;

import javax.inject.Singleton;

import com.github.airqs.base.BaseMybatisDao;
import com.github.airqs.entity.CityHour;

/**
 * @author Eric
 *
 */
@Singleton
public class CityHourRepo extends BaseMybatisDao {
	
	public CityHour getLastReportByCityId(Integer cityId){
		return (CityHour) sqlSession.selectOne("airqs.cityHour.getLastReportByCityId", cityId);
	}
	
	public List<CityHour> findLastReport(Integer cityId){
		return null;
	}
	
	public void add(CityHour cityHour){
		sqlSession.insert("airqs.cityHour.add", cityHour);
	}
	
	public void update(CityHour cityHour){
		sqlSession.update("airqs.cityHour.update", cityHour);
	}
}
