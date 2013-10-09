package com.mobile.tool.promo.dummy;

import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class GeoPoint {

	private String name;
	private double[] loc;
	
	public GeoPoint() {
	
	}
	
	public GeoPoint(String name, double[] loc) {
		super();
		this.name = name;
		this.loc = loc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double[] getLoc() {
		return loc;
	}
	public void setLoc(double[] loc) {
		this.loc = loc;
	}
	
	
}
