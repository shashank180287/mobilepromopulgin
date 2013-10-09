package com.mobile.tool.core.dao;

import java.util.List;

import com.mobile.tool.core.entity.SubCategoryDimension;

public interface SubCategoryDao {

	public SubCategoryDimension fetchSubCategoryByServiceTypeCodeAndSubCategoryName(String serviceTypeCode, String subCategoryName);

	public List<SubCategoryDimension> fetchAllSubCategoriesByServiceTypeCode(String serviceTypeCode);

	public void insertOrUpdate(SubCategoryDimension subCategoryDimension);
	
}
