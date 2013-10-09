package com.mobile.tool.inventory.response.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class InventorySearchResponse {

	@JsonProperty
	private String categoryName;
	
	@JsonProperty
	private String subCategoryName;
	
	@JsonProperty
	private List<InventorySearchItem> inventorySearchItems;

	public InventorySearchResponse(String categoryName, String subCategoryName,
			List<InventorySearchItem> inventorySearchItems) {
		super();
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
		this.inventorySearchItems = inventorySearchItems;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public List<InventorySearchItem> getInventorySearchItems() {
		return inventorySearchItems;
	}

}
