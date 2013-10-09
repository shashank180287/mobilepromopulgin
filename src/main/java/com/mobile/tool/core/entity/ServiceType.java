package com.mobile.tool.core.entity;

import java.sql.Date;
import java.util.Calendar;

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
	@NamedQuery(name=ServiceType.FETCH_ALL, query="from ServiceType")
})
@Table(name="service_type")
public class ServiceType {

	public static final String FETCH_ALL= "fetchAll";
	
	private Integer serviceTypeId;
	private String name;
	private String code;
	private Date createdAt;
	
	public ServiceType() {
		super();
		this.createdAt = new Date(Calendar.getInstance().getTimeInMillis());
	}
	
	public ServiceType(String name, String code) {
		super();
		this.name = name;
		this.code = code;
		this.createdAt = new Date(Calendar.getInstance().getTimeInMillis());
	}

	@Id
	@Column(name="service_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(Integer serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	
	@Column(name="code", unique=true)
	public String getCode() {
		return code;
	}
	
	@Column(name="service_name")
	public String getName() {
		return name;
	}

	@Column(name="created_ts")
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
