package com.mobile.tool.inventory.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Item {

	private Integer itemId;
	private String serviceTypeCode;
	private String subCategoryName;
	private String itemCode;
	private String itemName;
	private Float price;
	private String message;
	private Float effectivePrice;
	private String brand;

	public Item(Integer itemId, String serviceTypeCode, String subCategoryName,
			String itemCode, String itemName, Float price, String message,
			Float effectivePrice, String brand) {
		super();
		this.itemId = itemId;
		this.serviceTypeCode = serviceTypeCode;
		this.subCategoryName = subCategoryName;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.message = message;
		this.effectivePrice = effectivePrice;
		this.brand=brand;
	}

	public Integer getItemId() {
		return itemId;
	}


	public String getServiceTypeCode() {
		return serviceTypeCode;
	}


	public String getSubCategoryName() {
		return subCategoryName;
	}


	public String getItemCode() {
		return itemCode;
	}


	public String getItemName() {
		return itemName;
	}


	public Float getPrice() {
		return price;
	}


	public String getMessage() {
		return message;
	}


	public Float getEffectivePrice() {
		return effectivePrice;
	}

	public String getBrand() {
		return brand;
	}

	public String toString() {
		return new ToStringBuilder(this).append("itemId", itemId)
				.append("serviceTypeCode", serviceTypeCode).append("subCategoryName", subCategoryName)
				.append("itemCode", itemCode).append("itemName", itemName)
				.append("price", price).append("message", message).append("brand", brand).append("effectivePrice", effectivePrice).toString();
	}
}
