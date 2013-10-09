package com.mobile.tool.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;
import com.mobile.tool.core.model.SubCategoryModel;

@Component
public class SubCategoryService {

	@Autowired
	private DataHandler dataHandler;
	
	public SubCategoryModel[] fetchAllSubCategoriesForServiceType(String serviceTypeCode){
		List<SubCategoryDimension> subCategoryDimensions = dataHandler.fetchAllSubCategoriesForService(serviceTypeCode);
		List<SubCategoryModel> subCategoryModelList = new ArrayList<>();
		for (SubCategoryDimension subCategoryDimension : subCategoryDimensions) {
			subCategoryModelList.add(new SubCategoryModel(subCategoryDimension.getSubCategoryId(), serviceTypeCode, subCategoryDimension.getName(), subCategoryDimension.getCode()));
		}
		SubCategoryModel[] subCategoryModels = new SubCategoryModel[subCategoryModelList.size()];
		subCategoryModelList.toArray(subCategoryModels);
		return subCategoryModels;
	}

	public void insertSubCategoryDimension(SubCategoryModel subCategoryModel) {
		ServiceType serviceType = dataHandler.fetchServiceTypeByCode(subCategoryModel.getServiceTypeCode());
		SubCategoryDimension subCategoryDimension = new SubCategoryDimension(serviceType, subCategoryModel.getName(), subCategoryModel.getCode());
		dataHandler.insertSubCategoryDimension(subCategoryDimension);
	}
}
