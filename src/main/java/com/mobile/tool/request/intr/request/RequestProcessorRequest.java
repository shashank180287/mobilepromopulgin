package com.mobile.tool.request.intr.request;

import org.codehaus.jackson.annotate.JsonProperty;

public class RequestProcessorRequest {

	@JsonProperty
	private String requestType;
	
	@JsonProperty
	private String processorClass;
	
	@JsonProperty
	private Integer noOfContexts;
	
	@JsonProperty
	private Integer noOfSubContexts;

	public RequestProcessorRequest() {
		super();
	}
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getProcessorClass() {
		return processorClass;
	}

	public void setProcessorClass(String processorClass) {
		this.processorClass = processorClass;
	}

	public Integer getNoOfContexts() {
		return noOfContexts;
	}

	public void setNoOfContexts(Integer noOfContexts) {
		this.noOfContexts = noOfContexts;
	}

	public Integer getNoOfSubContexts() {
		return noOfSubContexts;
	}

	public void setNoOfSubContexts(Integer noOfSubContexts) {
		this.noOfSubContexts = noOfSubContexts;
	}
}
