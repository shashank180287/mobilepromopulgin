package com.mobile.tool.inventory.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mobile.tool.core.model.ICSVModel;

public class InventoryItemCSVModel implements ICSVModel{

	private String serviceTypeCode;
	private String subCategoryName;
	private String itemCode;
	private String itemName;
	private Double price;
	private String message;
	private Double effectivePrice;
	private String brand;

	public InventoryItemCSVModel() {
		super();
	}

	public InventoryItemCSVModel(String serviceTypeCode, String subCategoryName,
			String itemCode, String itemName, Double price, String message,
			Double effectivePrice, String brand) {
		super();
		this.serviceTypeCode = serviceTypeCode;
		this.subCategoryName = subCategoryName;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.brand = brand;
		this.message = message;
		if(effectivePrice==null)
			this.effectivePrice = price;
		else
			this.effectivePrice = effectivePrice;
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


	public Double getPrice() {
		return price;
	}


	public String getMessage() {
		return message;
	}


	public Double getEffectivePrice() {
		return effectivePrice;
	}

	public String getBrand() {
		return this.brand;
	}
	
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setEffectivePrice(Double effectivePrice) {
		this.effectivePrice = effectivePrice;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setPrice(BigDecimal price){
		this.price = price.doubleValue();
	}
	
	public void setEffectivePrice(BigDecimal effectivePrice){
		this.effectivePrice = effectivePrice.doubleValue();
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("serviceTypeCode", serviceTypeCode).append("subCategoryName", subCategoryName)
				.append("itemCode", itemCode).append("itemName", itemName)
				.append("price", price).append("message", message).append("price", price).append("effectivePrice", effectivePrice).append("brand", brand).toString();
	}

}
