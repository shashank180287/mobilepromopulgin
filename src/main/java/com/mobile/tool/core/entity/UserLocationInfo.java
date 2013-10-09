package com.mobile.tool.core.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_location_info")
public class UserLocationInfo {

	@Id
	private int id;
	@Column(name="user_id")
	
	private String userId;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@Column(name="visited_ts")
	private Timestamp visitedTs;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Timestamp getVisitedTs() {
		return visitedTs;
	}
	
	public void setVisitedTs(Timestamp visitedTs) {
		this.visitedTs = visitedTs;
	}
	
	
}
