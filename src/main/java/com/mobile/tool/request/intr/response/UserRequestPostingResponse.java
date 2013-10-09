package com.mobile.tool.request.intr.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserRequestPostingResponse {

	@JsonProperty
	private String status;
	
	@JsonProperty
	private String message;

	public UserRequestPostingResponse(String status, String message) {
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
