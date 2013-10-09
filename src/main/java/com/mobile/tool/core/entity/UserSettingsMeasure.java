package com.mobile.tool.core.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name=UserSettingsMeasure.FETCH_BY_USER_ID, query="from UserSettingsMeasure where userId= :userId")
})
@Table(name="user_settings_measure")
public class UserSettingsMeasure {

	public static final String FETCH_BY_USER_ID = "fetchByUserId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="service_type_codes")
	private String serviceTypeCodes;
	
	@Column(name="modified_ts")
	private Timestamp modifiedAt;

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

	public String getServiceTypeCodes() {
		return serviceTypeCodes;
	}

	public void setServiceTypeCodes(String serviceTypeCodes) {
		this.serviceTypeCodes = serviceTypeCodes;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
}
