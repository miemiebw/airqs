package com.github.airqs.manager;


public class Circle implements Shape{
	private Point centre;
	private int radius;//单位米
	public Circle(Point centre,int radius){
		this.centre = centre;
		this.radius = radius;
	}
	public Point getCentre() {
		return centre;
	}
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public boolean contains(Point p){
		double  dis = MapUtils.getDistance(centre.getX(), centre.getY(), p.getX(), p.getY());
		return radius > dis;
	}
}
