package com.mobile.tool.inventory.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mobile.tool.inventory.dao.InventoryItemDao;
import com.mobile.tool.inventory.entity.ItemMeasure;

public class InventoryItemDaoPSQLImpl implements InventoryItemDao {
	
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemMeasure> searchItemsByServiceTypeAndSubCategory(String serviceTypeName, String subCategoryName){
		return hibernateTemplate.findByNamedQueryAndNamedParam(ItemMeasure.SEARCH_ITEMS_FOR_CATEGORY, new String[]{"serviceTypeName","subCategoryName"}, new Object[]{serviceTypeName, subCategoryName});
	}

	@Override
	public void addAll(List<ItemMeasure> itemMeasures) {
		hibernateTemplate.saveOrUpdateAll(itemMeasures);
	}

	@Override
	public ItemMeasure fetchItemsByitemCode(String itemCode) {
		@SuppressWarnings("unchecked")
		List<ItemMeasure> itemMeasures = hibernateTemplate.findByNamedQueryAndNamedParam(ItemMeasure.SEARCH_ITEMS_BY_ITEM_CODE, "itemcode", itemCode);
		if(!itemMeasures.isEmpty())
			return itemMeasures.get(0);
		return null;
	}

	@Override
	public void insertOrUpdate(ItemMeasure itemMeasure) {
		hibernateTemplate.saveOrUpdate(itemMeasure);
	}

	@Override
	public void delete(ItemMeasure itemMeasure) {
		hibernateTemplate.delete(itemMeasure);
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
