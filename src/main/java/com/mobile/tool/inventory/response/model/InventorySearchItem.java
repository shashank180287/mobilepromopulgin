package com.mobile.tool.inventory.response.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class InventorySearchItem {

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
	
	public InventorySearchItem(String itemCode, String itemName,
			Float price, String message, Float effectivePrice, String brand) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.message = message;
		this.effectivePrice = effectivePrice;
		this.brand = brand;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventorySearchItem)) return false;

        InventorySearchItem inventorySearchItem = (InventorySearchItem) o;

        return new EqualsBuilder()
                .append(this.itemCode, inventorySearchItem.itemCode)
                .append(this.itemName, inventorySearchItem.itemName)
                .append(this.price, inventorySearchItem.price)
                .append(this.message, inventorySearchItem.message)
                .append(this.effectivePrice, inventorySearchItem.effectivePrice)
                .append(this.brand, inventorySearchItem.brand)
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
                .hashCode();
    }
    
}
