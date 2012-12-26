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
	public float GC[] = {80.23378f, 12.991821f};
	public float ICSR[] = {80.23257f, 12.991722f};
	public float LIB[] = {80.23396f, 12.991125f};
	public float CLT[] = {80.23218f, 12.989793f};
	public float OAT[] = {80.23374f, 12.989262f};
	public float SAC[] = {80.2377f, 12.989564f};
	
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
