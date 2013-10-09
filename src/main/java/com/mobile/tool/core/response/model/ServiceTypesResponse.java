package com.mobile.tool.core.response.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mobile.tool.core.model.ServiceTypeModel;

public class ServiceTypesResponse {

	@JsonProperty
	private ServiceTypeModel[] serviceTypes;

	public ServiceTypeModel[] getServiceTypes() {
		return serviceTypes;
	}

	public ServiceTypesResponse(ServiceTypeModel[] serviceTypes) {
		super();
		this.serviceTypes = serviceTypes;
	}
}
