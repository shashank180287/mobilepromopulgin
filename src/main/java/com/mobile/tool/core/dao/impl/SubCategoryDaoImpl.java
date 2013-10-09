package com.mobile.tool.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mobile.tool.core.dao.SubCategoryDao;
import com.mobile.tool.core.entity.SubCategoryDimension;

@Repository
public class SubCategoryDaoImpl implements SubCategoryDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	@SuppressWarnings("unchecked")
	public SubCategoryDimension fetchSubCategoryByServiceTypeCodeAndSubCategoryName(String serviceTypeCode, String subCategoryName) {
		List<SubCategoryDimension> subCategoryDimensions =  hibernateTemplate.findByNamedQueryAndNamedParam(SubCategoryDimension.FIND_BY_SERVICE_CODE_AND_SUB_CAT, new String[]{"serviceCode", "subCatName"}, new Object[]{serviceTypeCode, subCategoryName});
		if(!subCategoryDimensions.isEmpty())
			return subCategoryDimensions.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategoryDimension> fetchAllSubCategoriesByServiceTypeCode(
			String serviceTypeCode) {
		return hibernateTemplate.findByNamedQueryAndNamedParam(SubCategoryDimension.FIND_ALL_BY_SERVICE_CODE,"serviceCode", serviceTypeCode);
	}

	@Override
	public void insertOrUpdate(SubCategoryDimension subCategoryDimension) {
		hibernateTemplate.saveOrUpdate(subCategoryDimension);
	}

}
