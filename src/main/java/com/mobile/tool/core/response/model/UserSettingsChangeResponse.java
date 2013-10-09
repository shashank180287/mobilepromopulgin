package com.mobile.tool.core.response.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserSettingsChangeResponse {

	@JsonProperty
	private String status;
	
	@JsonProperty
	private String message;

	public UserSettingsChangeResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
