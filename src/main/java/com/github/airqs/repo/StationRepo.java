/**
 * 
 */
package com.github.airqs.repo;

import javax.inject.Singleton;

import com.github.airqs.base.BaseMybatisDao;
import com.github.airqs.entity.Station;

/**
 * @author Eric
 *
 */
@Singleton
public class StationRepo extends BaseMybatisDao {
	
	public Station getByName(String name){
		return (Station) sqlSession.selectOne("airqs.station.getByName", name);
	}
	
	public void add(Station station){
		sqlSession.insert("airqs.station.add", station);
	}
}
