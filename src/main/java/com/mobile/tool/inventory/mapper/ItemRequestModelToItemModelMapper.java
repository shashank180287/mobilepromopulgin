package com.mobile.tool.inventory.mapper;

import com.mobile.tool.inventory.model.Item;
import com.mobile.tool.inventory.request.model.InventoryItemRequestModel;

public class ItemRequestModelToItemModelMapper {

	public static Item mapItemRequestModelToItem(InventoryItemRequestModel inventoryItemRequestModel){
		return new Item(null, inventoryItemRequestModel.getServiceCode(), inventoryItemRequestModel.getSubCategoryName(), inventoryItemRequestModel.getItemCode(), inventoryItemRequestModel.getItemName(), inventoryItemRequestModel.getPrice(), inventoryItemRequestModel.getMessage()
											, inventoryItemRequestModel.getEffectivePrice(), inventoryItemRequestModel.getBrand());
	}
	
}
