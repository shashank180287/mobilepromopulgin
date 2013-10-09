package com.mobile.tool.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;

@Entity
@NamedQueries({
	@NamedQuery(name=ItemMeasure.SEARCH_ITEMS_FOR_CATEGORY, query="from ItemMeasure where serviceType.name= :serviceTypeName and subCategoryDimension.name= :subCategoryName"),
	@NamedQuery(name=ItemMeasure.SEARCH_ITEMS_BY_ITEM_CODE, query="from ItemMeasure where itemCode= :itemcode")
})
@Table(name="item_measure")
public class ItemMeasure {

	public static final String SEARCH_ITEMS_BY_ITEM_CODE = "fetchItemsByItemCode";
	public static final String SEARCH_ITEMS_FOR_CATEGORY ="fetchItemsByServiceTypeAndSubCategory";
	private Integer itemId;
	private ServiceType serviceType;
	private SubCategoryDimension subCategoryDimension;
	private String itemCode;
	private String itemName;
	private Float price;
	private String message;
	private Float effectivePrice;
	private String brandName;
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getItemId() {
		return itemId;
	}

	@ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
	public ServiceType getServiceType() {
		return serviceType;
	}

	@ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
	public SubCategoryDimension getSubCategoryDimension() {
		return subCategoryDimension;
	}
	
	@Column(name="item_code")
	public String getItemCode() {
		return itemCode;
	}

	@Column(name="name")
	public String getItemName() {
		return itemName;
	}

	@Column(name="price")
	public Float getPrice() {
		return price;
	}

	@Column(name="message")
	public String getMessage() {
		return message;
	}

	@Column(name="effective_price")
	public Float getEffectivePrice() {
		return effectivePrice;
	}

	@Column(name="brand")
	public String getBrandName() {
		return brandName;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public void setSubCategoryDimension(SubCategoryDimension subCategoryDimension) {
		this.subCategoryDimension = subCategoryDimension;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setEffectivePrice(Float effectivePrice) {
		this.effectivePrice = effectivePrice;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
