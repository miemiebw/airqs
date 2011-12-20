/**
 * 
 */
package com.github.airqs.crawl;

import java.net.URLEncoder;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;


/**
 * @author Eric
 *
 */
public class AirFinderTestCase {
	
	//@Test
	public void findCityHour(){
		AirFinder finder = new AirFinder();
		List<AirCityHour> airCityHours = finder.findCityHour();
		for (AirCityHour airCityHour : airCityHours) {
			System.out.println(airCityHour);
		}
		
	}
	//@Test
	public void findStationHour(){
		AirFinder finder = new AirFinder();
		List<AirStationHour> airCityHours = finder.findStationHour("山东");
		for (AirStationHour airStationHour : airCityHours) {
			System.out.println(airStationHour);
		}
	}
}
