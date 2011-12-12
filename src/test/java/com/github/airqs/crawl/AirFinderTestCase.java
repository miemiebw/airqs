/**
 * 
 */
package com.github.airqs.crawl;

import java.util.List;

import org.junit.Test;


/**
 * @author Eric
 *
 */
public class AirFinderTestCase {
	
	@Test
	public void find(){
		AirFinder finder = new AirFinder();
		List<AirCityHour> airCityHours = finder.findCityHour();
		for (AirCityHour airCityHour : airCityHours) {
			System.out.println(airCityHour);
		}
		
	}
}
