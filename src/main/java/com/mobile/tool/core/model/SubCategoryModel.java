package com.mobile.tool.core.model;

import org.codehaus.jackson.annotate.JsonProperty;


public class SubCategoryModel {
	
	@JsonProperty
	private Integer subCategoryId;
	
	@JsonProperty
	private String serviceTypeCode;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String code;
	
	public SubCategoryModel() {
		super();
	}
	
	public SubCategoryModel(Integer subCategoryId, String serviceTypeCode,
			String name, String code) {
		super();
		this.subCategoryId = subCategoryId;
		this.serviceTypeCode = serviceTypeCode;
		this.name = name;
		this.code = code;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public String getServiceTypeCode() {
		return serviceTypeCode;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	
	
}
