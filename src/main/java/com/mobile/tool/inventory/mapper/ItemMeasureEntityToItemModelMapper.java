package com.mobile.tool.inventory.mapper;

import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;
import com.mobile.tool.inventory.entity.ItemMeasure;
import com.mobile.tool.inventory.model.Item;

public class ItemMeasureEntityToItemModelMapper {

	public static Item mapItemMeasureToItem(ItemMeasure itemMeasure){
		return new Item( itemMeasure.getItemId(), itemMeasure.getServiceType().getCode(), itemMeasure.getSubCategoryDimension().getName(), itemMeasure.getItemCode(), itemMeasure.getItemName(), itemMeasure.getPrice(), itemMeasure.getMessage(), itemMeasure.getEffectivePrice(), itemMeasure.getBrandName());
	}
	
	public static ItemMeasure mapItemToItemMeasure(Item item, ServiceType serviceType, SubCategoryDimension subCategoryDimension){
		ItemMeasure itemMeasure =  new ItemMeasure();
		itemMeasure.setBrandName(item.getBrand());
		itemMeasure.setEffectivePrice(item.getEffectivePrice());
		itemMeasure.setItemCode(item.getItemCode());
		itemMeasure.setItemName(item.getItemName());
		itemMeasure.setMessage(item.getMessage());
		itemMeasure.setPrice(item.getPrice());
		itemMeasure.setServiceType(serviceType);
		itemMeasure.setSubCategoryDimension(subCategoryDimension);
		return itemMeasure;
	}
}

