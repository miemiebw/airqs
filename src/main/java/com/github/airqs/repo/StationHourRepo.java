/**
 * 
 */
package com.github.airqs.repo;

import javax.inject.Singleton;


import com.github.airqs.base.BaseMybatisDao;
import com.github.airqs.entity.StationHour;

/**
 * @author Eric
 *
 */
@Singleton
public class StationHourRepo extends BaseMybatisDao {

	public StationHour getLastReportByStationId(Integer stationId){
		return (StationHour) sqlSession.selectOne("airqs.stationHour.getLastReportByStationId", stationId);
	}
	
	public void add(StationHour stationHour){
		sqlSession.insert("airqs.stationHour.add", stationHour);
	}
	
	public void update(StationHour stationHour){
		sqlSession.update("airqs.stationHour.update", stationHour);
	}
	
	
}
