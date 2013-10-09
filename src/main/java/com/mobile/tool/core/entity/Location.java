package com.mobile.tool.core.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name="location")
public class Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8698615633277504183L;
	
	private String locationId;
	private String name;
	private double latitude;
	private double longitude;
	
	public Location() {
		super();
	}
	
	public Location(JSONObject object) throws JSONException{
		super();
		this.locationId = object.getString("locationId");
		this.name = object.getString("name");
		this.latitude = object.getDouble("latitude");
		this.longitude = object.getDouble("longitude");
	}
	
	public Location(double latitude, double longitude) {
		this.locationId = UUID.randomUUID().toString();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	@Id
	@Column(name="location_id")
	public String getLocationId() {
		return locationId;
	}
	
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="latitude")
	public double getLatitude() {
		return latitude;
	}

	@Column(name="longitude")
	public double getLongitude() {
		return longitude;
	}

	
	public String generateLocationCode() {
		return latitude+":"+longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object loc) {
		if(!(loc instanceof Location))
			return false;
		Location location = (Location) loc;
		if(this.latitude==location.getLatitude() && this.longitude==location.getLongitude())
			return true;
		return false;
	}
	
	public String toJSONString() {
		StringBuilder jsonObject = new StringBuilder();
		jsonObject.append("{");
		jsonObject.append("\t\"locationId\" : \""+ locationId+"\",\n");
		jsonObject.append("\t\"latitude\" : "+ latitude+",\n");
		jsonObject.append("\t\"longitude\" : "+ longitude+",\n");
		jsonObject.append("\t\"name\" : "+ (name!=null?"\""+name+"\"":null));
		jsonObject.append("}");
		return jsonObject.toString();
	}
}
