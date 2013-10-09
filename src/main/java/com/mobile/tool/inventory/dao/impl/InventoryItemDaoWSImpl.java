package com.mobile.tool.inventory.dao.impl;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;

import com.mobile.tool.inventory.dao.InventoryItemDao;
import com.mobile.tool.inventory.entity.ItemMeasure;

public class InventoryItemDaoWSImpl  implements InventoryItemDao {
	
	@Override
	public List<ItemMeasure> searchItemsByServiceTypeAndSubCategory(String serviceTypeName, String subCategoryName){
		throw new NotImplementedException("Method not suppoted as web service not available");
	}

	@Override
	public void addAll(List<ItemMeasure> itemMeasures) {
		throw new NotImplementedException("Method not suppoted as web service not availablee");	
	}

	@Override
	public ItemMeasure fetchItemsByitemCode(String itemCode) {
		throw new NotImplementedException("Method not suppoted as web service not available");
	}

	@Override
	public void insertOrUpdate(ItemMeasure itemMeasure) {
		throw new NotImplementedException("Method not suppoted as web service not available");
	}

	@Override
	public void delete(ItemMeasure itemMeasure) {
		throw new NotImplementedException("Method not suppoted as web service not available");
	}
}
