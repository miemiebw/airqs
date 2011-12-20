/**
 * 
 */
package com.github.airqs.manager;


/**
 * 地图工具类
 * @author Eric.Lee
 * 
 */
public class MapUtils {

	private final static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static Double getDistance(Double lat1, Double lng1, Double lat2,
			Double lng2) {
		
		if(lat1 == null || lng1 == null || lat2 == null || lng2 ==null){
			return Double.MAX_VALUE;
		}
		
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 1000) ;
		return s;
	}
	
	public static Point getPoint(double startStone, double endStone, 
			Point[] points, double inputStone){
		double seg = Math.abs(startStone - endStone) / points.length;
		double index = Math.abs(inputStone - startStone) / seg;
		return points[(int)index];
	}
}
