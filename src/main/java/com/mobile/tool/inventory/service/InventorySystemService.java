package com.mobile.tool.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;
import com.mobile.tool.inventory.entity.ItemMeasure;
import com.mobile.tool.inventory.mapper.ItemMeasureEntityToItemModelMapper;
import com.mobile.tool.inventory.model.Item;

@Service
public class InventorySystemService {

	@Autowired
	private DataHandler dataHandler;
	
	public List<Item> fetchItemsByCategoryAndSubCategoryName(String serviceTypeName, String subCategoryName) {
		List<ItemMeasure> itemMeasures = dataHandler.fetchItemsByCategoryAndSubCategoryName(serviceTypeName, subCategoryName);
		List<Item> itemsList = new ArrayList<Item>();
		for (ItemMeasure itemMeasure : itemMeasures) {
			itemsList.add(ItemMeasureEntityToItemModelMapper.mapItemMeasureToItem(itemMeasure));
		}
		return itemsList;
	}

	public Item fetchItemDetailsByItemCode(String itemCode) {
		ItemMeasure itemMeasure = dataHandler.fetchItemsByitemCode(itemCode);
		return ItemMeasureEntityToItemModelMapper.mapItemMeasureToItem(itemMeasure);
	}

	public boolean insertInventoryItemMeasure(Item item) {
		ServiceType serviceType = dataHandler.fetchServiceTypeByCode(item.getServiceTypeCode());
		if(serviceType==null)
			throw new IllegalArgumentException(String.format("Service Type with type %s not present.",item.getServiceTypeCode()));
		
		SubCategoryDimension subCategoryDimension = dataHandler.fetchSubCategoryByServiceTypeAndSubCategoryName(serviceType, item.getSubCategoryName());
		if(subCategoryDimension==null)
			throw new IllegalArgumentException(String.format("Sub Category with name %s not present.", item.getSubCategoryName()));
		
		ItemMeasure itemMeasure = ItemMeasureEntityToItemModelMapper.mapItemToItemMeasure(item, serviceType, subCategoryDimension);
		dataHandler.insertOrUpdateItemMeasure(itemMeasure);
		return true;
	}

	public boolean updateInventoryItemMeasure(Item item) {
		ItemMeasure itemMeasure = dataHandler.fetchItemsByitemCode(item.getItemCode());
		if(itemMeasure==null)
			throw new IllegalArgumentException(String.format("No item present with code as %s",item.getItemCode()));
		itemMeasure.setBrandName(item.getBrand());
		itemMeasure.setEffectivePrice(item.getEffectivePrice());
		itemMeasure.setItemName(item.getItemName());
		itemMeasure.setMessage(item.getMessage());
		itemMeasure.setPrice(item.getPrice());
		dataHandler.insertOrUpdateItemMeasure(itemMeasure);
		return true;
	}

	public boolean deleteInventoryItemMeasure(String itemCode) {
		ItemMeasure itemMeasure = dataHandler.fetchItemsByitemCode(itemCode);
		if(itemMeasure==null)
			throw new IllegalArgumentException(String.format("No item present with code as %s",itemCode));
		dataHandler.deleteItemMeasure(itemMeasure);
		return true;
	}

}
