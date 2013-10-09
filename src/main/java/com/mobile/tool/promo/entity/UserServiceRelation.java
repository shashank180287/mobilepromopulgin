package com.mobile.tool.promo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.mobile.tool.core.entity.ServiceType;

@Entity
@NamedQueries({
	@NamedQuery(name=UserServiceRelation.FETCH_SERVICE_CODE_ORDER_BY_COUNT, query="select serviceType.code from UserServiceRelation where userId= :userId group by serviceType.code order by count(*) desc")
	
})
@Table(name="user_service_relation")
public class UserServiceRelation {

	public static final String FETCH_SERVICE_CODE_ORDER_BY_COUNT = "fetchServiceCodeListOrderByCount";
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="service_name")
	private String service;
	
	@ManyToOne
	@JoinColumn(name="service_type_id")
	private ServiceType serviceType;
	
	@Column(name="visited_ts")
	private Timestamp visitedTS;
	
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
	
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	public ServiceType getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	
	public Timestamp getVisitedTS() {
		return visitedTS;
	}

	public void setVisitedTS(Timestamp visitedTS) {
		this.visitedTS = visitedTS;
	}
	
	
	
}
