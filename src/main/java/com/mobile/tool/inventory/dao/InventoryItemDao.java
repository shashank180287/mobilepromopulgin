package com.mobile.tool.inventory.dao;

import java.util.List;

import com.mobile.tool.inventory.entity.ItemMeasure;


public interface InventoryItemDao {

	public List<ItemMeasure> searchItemsByServiceTypeAndSubCategory(String serviceTypeName, String subCategoryName);

	public void addAll(List<ItemMeasure> itemMeasures);

	public ItemMeasure fetchItemsByitemCode(String itemCode);

	public void insertOrUpdate(ItemMeasure itemMeasure);

	public void delete(ItemMeasure itemMeasure);
	
}
