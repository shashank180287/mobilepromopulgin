package com.mobile.tool.promo.response.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mobile.tool.core.response.model.ServiceTypesResponse;
import com.mobile.tool.inventory.response.model.InventorySearchResponse;

public class PromoToolStatusResponse {

	@JsonProperty
	private char isServiceAvailable;
	
	@JsonProperty
	private ServiceTypesResponse serviceTypesResponse;
	
	@JsonProperty
	private InventorySearchResponse inventorySearchResponse;

	public char getIsServiceAvailable() {
		return isServiceAvailable;
	}

	public PromoToolStatusResponse withServiceAvailableFlag(char isServiceAvailable) {
		this.isServiceAvailable = isServiceAvailable;
		return this;
	}

	public ServiceTypesResponse getServiceTypesResponse() {
		return serviceTypesResponse;
	}

	public PromoToolStatusResponse withServiceTypesResponse(ServiceTypesResponse serviceTypesResponse) {
		this.serviceTypesResponse = serviceTypesResponse;
		return this;
	}

	public InventorySearchResponse getInventorySearchResponse() {
		return inventorySearchResponse;
	}

	public PromoToolStatusResponse withInventorySearchResponse(InventorySearchResponse inventorySearchResponse) {
		this.inventorySearchResponse = inventorySearchResponse;
		return this;
	}
	
	
}
