package com.mobile.tool.core.request.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserSettingsChangeRequest {

	@JsonProperty
	private String userId;
	
	@JsonProperty
	private String userSelectedServiceTypes; //comma seperated

	public UserSettingsChangeRequest() {
		super();
	}

	public UserSettingsChangeRequest(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserSelectedServiceTypes() {
		return userSelectedServiceTypes;
	}


	public void setUserSelectedServiceTypes(String userSelectedServiceTypes) {
		this.userSelectedServiceTypes = userSelectedServiceTypes;
	}

	public String getUserId() {
		return userId;
	}
	
	
}
