package com.mobile.tool.inventory.response.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class InventoryItemOperationResponse {

	@JsonProperty
	private String status;
	
	@JsonProperty
	private String message;

	public InventoryItemOperationResponse(String status, String message) {
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
