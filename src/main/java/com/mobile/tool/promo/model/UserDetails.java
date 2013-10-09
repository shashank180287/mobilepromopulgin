package com.mobile.tool.promo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.tool.core.entity.Location;

public class UserDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8428858681185736462L;
	private String userId;
	private Location currentLocation;
	private List<Location> lastThreeLocation;
	
	public UserDetails(String id) {
		this.userId = id;
		lastThreeLocation = new ArrayList<Location>(3);
	}
	
	public UserDetails(JSONObject object) throws JSONException{
		super();
		this.userId = object.getString("userId");
		this.currentLocation = new Location(object.getJSONObject("currentLocation"));
		JSONArray array = object.getJSONArray("lastThreeLocation");
		this.lastThreeLocation= new ArrayList<Location>();
		for (int i = 0; i < array.length(); i++) {
			this.lastThreeLocation.add(new Location(array.getJSONObject(i)));
		}
	}
	
	public UserDetails(String id, Location currentLocation) {
		this.userId = id;
		this.currentLocation = currentLocation;
		lastThreeLocation = new ArrayList<Location>(3);
	}
	
	public String getUserId() {
		return userId;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public List<Location> getLastThreeLocation() {
		return lastThreeLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.lastThreeLocation.add(this.currentLocation);
		this.currentLocation = currentLocation;
	}

	public String toJSONString() {
		StringBuilder jsonObject = new StringBuilder();
		jsonObject.append("{");
		jsonObject.append("\t\"userId\" : "+ (userId!=null?"\""+userId+"\"":null)+",\n");
		jsonObject.append("\t\"currentLocation\" : "+ (currentLocation!=null?currentLocation.toJSONString():null)+",\n");
		if(lastThreeLocation!=null){
			jsonObject.append("\t\"lastThreeLocation\" : [");
			for (Location lastLocation : lastThreeLocation) {
				jsonObject.append((lastLocation!=null?lastLocation.toJSONString()+((lastThreeLocation.indexOf(lastLocation)!= lastThreeLocation.size()-1)?",\n":""):""));
			}
			jsonObject.append("]");
		}
		jsonObject.append("}");
		return jsonObject.toString();
	}
}
