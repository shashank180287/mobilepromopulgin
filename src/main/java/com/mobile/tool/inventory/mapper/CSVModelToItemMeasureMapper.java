package com.mobile.tool.inventory.mapper;

import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;
import com.mobile.tool.inventory.entity.ItemMeasure;
import com.mobile.tool.inventory.model.InventoryItemCSVModel;

public class CSVModelToItemMeasureMapper {

	public static ItemMeasure mapCSVModelToItemMeasure(InventoryItemCSVModel inventoryItemCSVModel, ServiceType serviceType, SubCategoryDimension subCategoryDimension) {
		ItemMeasure itemMeasure = new ItemMeasure();
		itemMeasure.setEffectivePrice(inventoryItemCSVModel.getEffectivePrice().floatValue());
		itemMeasure.setItemCode(inventoryItemCSVModel.getItemCode());
		itemMeasure.setItemName(inventoryItemCSVModel.getItemName());
		itemMeasure.setMessage(inventoryItemCSVModel.getMessage());
		itemMeasure.setPrice(inventoryItemCSVModel.getPrice().floatValue());
		itemMeasure.setBrandName(inventoryItemCSVModel.getBrand());
		itemMeasure.setServiceType(serviceType);
		itemMeasure.setSubCategoryDimension(subCategoryDimension);
		return itemMeasure;
	}
}
