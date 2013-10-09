package com.mobile.tool.request.intr.request;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserRequestPostingRequest {

	@JsonProperty
	private String id;
	
	@JsonProperty
	private String code;
	
	@JsonProperty
	private String subCat;
	
	@JsonProperty
	private String reqTypeName;
	
	@JsonProperty
	private String ctx;
	
	@JsonProperty
	private String subCtx;

	public UserRequestPostingRequest() {
		super();
	}

	public UserRequestPostingRequest(String id, String code, String subCat,
			String reqTypeName, String ctx, String subCtx) {
		super();
		this.id = id;
		this.code = code;
		this.subCat = subCat;
		this.reqTypeName = reqTypeName;
		this.ctx = ctx;
		this.subCtx = subCtx;
	}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getSubCat() {
		return subCat;
	}

	public String getReqTypeName() {
		return reqTypeName;
	}

	public String getCtx() {
		return ctx;
	}

	public String getSubCtx() {
		return subCtx;
	}

}
