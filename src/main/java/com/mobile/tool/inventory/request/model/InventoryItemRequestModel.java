package com.mobile.tool.inventory.request.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class InventoryItemRequestModel {

	@JsonProperty
	private String serviceCode;
	
	@JsonProperty
	private String subCategoryName;
	
	@JsonProperty
	private String itemCode;
	
	@JsonProperty
	private String itemName;
	
	@JsonProperty
	private Float price;
	
	@JsonProperty
	private String message;
	
	@JsonProperty
	private Float effectivePrice;

	@JsonProperty
	private String brand;
	
	public InventoryItemRequestModel() {
		super();
	}
	
	public InventoryItemRequestModel(String itemCode, String itemName,
			Float price, String message, Float effectivePrice, String brand, String serviceCode, String subCategoryName) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.message = message;
		this.effectivePrice = effectivePrice;
		this.brand = brand;
		this.serviceCode = serviceCode;
		this.subCategoryName = subCategoryName;
	}
	
    public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Float getEffectivePrice() {
		return effectivePrice;
	}

	public void setEffectivePrice(Float effectivePrice) {
		this.effectivePrice = effectivePrice;
	}

	public String getBrand() {
		return brand;
	}
	
	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryItemRequestModel)) return false;

        InventoryItemRequestModel inventoryItemReqModel = (InventoryItemRequestModel) o;

        return new EqualsBuilder()
                .append(this.itemCode, inventoryItemReqModel.itemCode)
                .append(this.itemName, inventoryItemReqModel.itemName)
                .append(this.price, inventoryItemReqModel.price)
                .append(this.message, inventoryItemReqModel.message)
                .append(this.effectivePrice, inventoryItemReqModel.effectivePrice)
                .append(this.brand, inventoryItemReqModel.brand)
                .append(this.serviceCode, inventoryItemReqModel.serviceCode)
                .append(this.subCategoryName, inventoryItemReqModel.subCategoryName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.itemCode)
                .append(this.itemName)
                .append(this.price)
                .append(this.message)  
                .append(this.effectivePrice)
                .append(this.brand)
                .append(this.brand)
                .append(this.serviceCode)
                .append(this.subCategoryName)
                .hashCode();
    }
    
}
