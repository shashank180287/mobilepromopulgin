package com.mobile.tool.inventory.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;
import com.mobile.tool.core.model.ICSVModel;
import com.mobile.tool.core.util.FileUploader;
import com.mobile.tool.inventory.entity.ItemMeasure;
import com.mobile.tool.inventory.mapper.CSVModelToItemMeasureMapper;
import com.mobile.tool.inventory.model.InventoryItemCSVModel;

@Component
public class InventoryItemsFileUploader extends FileUploader {

	public static final String MODULE_KEY = "inventoryItemModule";
	@Autowired 
	private DataHandler dataHandler;
	
	@Override
	protected boolean isHeaderElementUsedForMapping() {
		return true;
	}

	@Override
	protected CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[] { 
                new NotNull(), // serviceTypeCode
                new NotNull(), // subCategoryName
                new UniqueHashCode(), // itemCode
                new NotNull(), // itemName
                new NotNull(new ParseBigDecimal()), // price
                new Optional(), // message
                new Optional(new ParseBigDecimal()), // effectivePrice
                new NotNull() // brand
        };
        return processors;
	}

	@Override
	protected Class<? extends ICSVModel> getCSVModelClass() {
		return InventoryItemCSVModel.class;
	}

	@Override
	protected boolean processReadCSVRecords(List<ICSVModel> readCSVModelList) {
		try{
			List<ItemMeasure> itemMeasures = new ArrayList<ItemMeasure>();
			for (ICSVModel icsvModel : readCSVModelList) {
				InventoryItemCSVModel inventoryModel = (InventoryItemCSVModel) icsvModel;
				ServiceType serviceType = dataHandler.fetchServiceTypeByCode(inventoryModel.getServiceTypeCode());
				SubCategoryDimension subCategoryDimension = dataHandler.fetchSubCategoryByServiceTypeAndSubCategoryName(serviceType, inventoryModel.getSubCategoryName());
				itemMeasures.add(CSVModelToItemMeasureMapper.mapCSVModelToItemMeasure(inventoryModel, serviceType, subCategoryDimension));
			}
			dataHandler.addAllItemMeasures(itemMeasures);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	protected String[] getCSVFileColumnsNamesOrder() {
		return new String[]{"serviceTypeCode","subCategoryName","itemCode","itemName","price","message","effectivePrice","brand"};
	}
}
