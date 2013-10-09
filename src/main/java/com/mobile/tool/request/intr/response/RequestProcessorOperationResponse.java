package com.mobile.tool.request.intr.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class RequestProcessorOperationResponse {

	@JsonProperty
	private String status;
	
	@JsonProperty
	private String message;

	public RequestProcessorOperationResponse(String status, String message) {
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
