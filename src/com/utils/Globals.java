package com.utils;

/*
 * Provision for global variables.
 * Currently used to handle which location was clicked on map 
 */

public class Globals {
	private static Globals instance;
	
	private int location; //enum index (starting from 1) of locations below
	
	private Globals() {} //Restricts constructor init
	
	//Hard coded geo
	public float GC[] = {80.23352f, 12.991372f};
	public float ICSR[] = {80.23089f, 12.9918139f};
	public float LIB[] = {80.234f, 12.990443f};
	
  	//Use 0.4 the distance between LIB and GC as touch resolution
    public double resolution = 0.4*(Math.pow((LIB[0]-GC[0]), 2) + Math.pow((LIB[1]-GC[1]), 2));
	
	public void setLocation(int d) {
		this.location = d;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public static synchronized Globals getInstance() {
		if(instance == null) instance = new Globals();
		return instance;
	}
}
