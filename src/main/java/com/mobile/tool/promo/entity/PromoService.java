package com.mobile.tool.promo.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class PromoService {

	private String serviceId;
	private String name;
	private String serviceType;
	private String serviceMessage;
	private Date validityStartDate;
	private Date validityEndDate;
	private Date applicableStartTime;
	private Date applicableEndTime;
	private List<String> applicableDays;
	private double[] location;
	private String businessType;
	private boolean isPremium;
	
	public PromoService(){
		
	}

	public PromoService(String name, String serviceType, String serviceMessage, 
			Date validityStartDate, Date validityEndDate,
			Date applicableStartTime, Date applicableEndTime,
			List<String> applicableDays, double[] location,
			String businessType, boolean isPremium) {
		this.serviceId = UUID.randomUUID().toString();
		this.name = name;
		this.serviceType = serviceType;
		this.serviceMessage = serviceMessage;
		this.validityStartDate = validityStartDate;
		this.validityEndDate = validityEndDate;
		this.applicableStartTime = applicableStartTime;
		this.applicableEndTime = applicableEndTime;
		this.applicableDays = applicableDays;
		this.location = location;
		this.businessType = businessType;
		this.isPremium = isPremium;
	}

	public String getServiceMessage() {
		return serviceMessage;
	}


	public String getServiceId() {
		return serviceId;
	}


	public String getName() {
		return name;
	}


	public String getServiceType() {
		return serviceType;
	}


	public Date getValidityStartDate() {
		return validityStartDate;
	}


	public Date getValidityEndDate() {
		return validityEndDate;
	}


	public Date getApplicableStartTime() {
		return applicableStartTime;
	}


	public Date getApplicableEndTime() {
		return applicableEndTime;
	}


	public List<String> getApplicableDays() {
		return applicableDays;
	}


	public double[] getLocation() {
		return location;
	}


	public String getBusinessType() {
		return businessType;
	}


	public boolean isPremium() {
		return isPremium;
	}


	@Override
	public String toString() {
		JSONObject promoServiceJsonObj = new JSONObject(this);
		return promoServiceJsonObj.toString();
	}
}
