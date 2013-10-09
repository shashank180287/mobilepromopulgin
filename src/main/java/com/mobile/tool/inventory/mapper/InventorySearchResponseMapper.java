package com.mobile.tool.inventory.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.inventory.model.Item;
import com.mobile.tool.inventory.response.model.InventorySearchItem;
import com.mobile.tool.inventory.response.model.InventorySearchResponse;

public class InventorySearchResponseMapper {

	public static InventorySearchResponse mapToInventorySearchResponse(List<Item> itemsList, String categoryName, String subCategoryName){
		List<InventorySearchItem> inventorySearchItems = new ArrayList<InventorySearchItem>();
		for (Item item : itemsList) {
			inventorySearchItems.add(mapItemModelToInventorySearchItem(item));
		}
		return new InventorySearchResponse(categoryName, subCategoryName, inventorySearchItems);
	}

	public static InventorySearchItem mapItemModelToInventorySearchItem(Item item) {
		return new InventorySearchItem(item.getItemCode(), item.getItemName(), item.getPrice(), item.getMessage(), item.getEffectivePrice(), item.getBrand());
	}
}
