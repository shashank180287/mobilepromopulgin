package com.mobile.tool.inventory.request.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class InventorySearchRequest {

	@JsonProperty
	private String categoryName;
	
	@JsonProperty
	private String subCategoryName;
	
	public InventorySearchRequest() {
		super();
	}

	public InventorySearchRequest(String categoryName, String subCategoryName) {
		super();
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getSubCategoryName() {
		return subCategoryName;
	}
	
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventorySearchRequest)) return false;

        InventorySearchRequest inventorySearchRequest = (InventorySearchRequest) o;

        return new EqualsBuilder()
                .append(this.categoryName, inventorySearchRequest.categoryName)
                .append(this.subCategoryName, inventorySearchRequest.subCategoryName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.categoryName)
                .append(this.subCategoryName)
                .hashCode();
    }
}
