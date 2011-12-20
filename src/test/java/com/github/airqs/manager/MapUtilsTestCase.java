package com.github.airqs.manager;

import org.junit.Test;

import com.github.airqs.manager.MapUtils;

public class MapUtilsTestCase {

	@Test
	public void getDistance(){
		double disc = MapUtils.getDistance(31.22, 121.48, 39.90, 116.40);
		System.out.println(disc);
	}
}
