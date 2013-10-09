package com.mobile.tool.request.intr.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class RequestProcessorDetails {

	@JsonProperty
	private String name;
	
	@JsonProperty
	private String selection;

	public RequestProcessorDetails(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}
	
}
