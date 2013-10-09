package com.mobile.tool.inventory.dao.impl;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mobile.tool.inventory.dao.InventoryItemDao;
import com.mobile.tool.inventory.entity.ItemMeasure;

public class InventoryItemDaoTPDBImpl  implements InventoryItemDao {
	
	private HibernateTemplate tpDBHibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemMeasure> searchItemsByServiceTypeAndSubCategory(String serviceTypeName, String subCategoryName){
		return tpDBHibernateTemplate.findByNamedQueryAndNamedParam(ItemMeasure.SEARCH_ITEMS_FOR_CATEGORY, new String[]{"serviceTypeName","subCategoryName"}, new Object[]{serviceTypeName, subCategoryName});
	}

	@Override
	public void addAll(List<ItemMeasure> itemMeasures) {
		throw new NotImplementedException("Method not suppoted for third party database");
	}

	@Override
	public ItemMeasure fetchItemsByitemCode(String itemCode) {
		throw new NotImplementedException("Method not suppoted for third party database");
	}

	@Override
	public void insertOrUpdate(ItemMeasure itemMeasure) {
		throw new NotImplementedException("Method not suppoted for third party database");
	}

	@Override
	public void delete(ItemMeasure itemMeasure) {
		throw new NotImplementedException("Method not suppoted for third party database");
	}
}
