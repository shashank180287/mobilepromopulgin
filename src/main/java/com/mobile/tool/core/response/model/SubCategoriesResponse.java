package com.mobile.tool.core.response.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.mobile.tool.core.model.SubCategoryModel;

public class SubCategoriesResponse {

	@JsonProperty
	private String serviceTypeCode;
	
	@JsonProperty
	private SubCategoryModel[] subCategories;

	public SubCategoriesResponse(String serviceTypeCode,
			SubCategoryModel[] subCategoryModels) {
		super();
		this.serviceTypeCode = serviceTypeCode;
		this.subCategories = subCategoryModels;
	}

	public String getServiceTypeCode() {
		return serviceTypeCode;
	}

	public SubCategoryModel[] getSubCategoryModels() {
		return subCategories;
	}

	
}
