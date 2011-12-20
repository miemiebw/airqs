package com.github.airqs.manager;

import javax.inject.Inject;

import org.junit.Test;

import com.github.airqs.entity.Station;
import com.github.airqs.test.BaseTestRunner;

public class AirManagerTestCase extends BaseTestRunner{

	@Inject
	private AirManager airManager;
	
	
	@Test
	public void getStationByLatLng(){
		Station station = airManager.getStationByLatLng(36.730129, 117.040898);
		System.out.println(station);
	}
}
