package com.mobile.tool.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.tool.core.model.SubCategoryModel;
import com.mobile.tool.core.response.model.SubCategoriesResponse;
import com.mobile.tool.core.response.model.ToolOperationResponse;
import com.mobile.tool.core.service.SubCategoryService;

@Controller
@RequestMapping(value="/subcategory")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;
	
	@RequestMapping(value="/{servicecode}/", method= RequestMethod.GET)
	public @ResponseBody SubCategoriesResponse fetchAllSubCategoryByServiceType(@PathVariable(value="servicecode") String serviceTypeCode){
		SubCategoryModel[] subCategoryModels = subCategoryService.fetchAllSubCategoriesForServiceType(serviceTypeCode);
		return new SubCategoriesResponse(serviceTypeCode, subCategoryModels);
	}
	
	@RequestMapping(value="/", method= RequestMethod.POST)
	public @ResponseBody ToolOperationResponse addSubCategory(@RequestBody SubCategoryModel subCategoryModel){
		try{
			subCategoryService.insertSubCategoryDimension(subCategoryModel);
			return new ToolOperationResponse("SUCCESS", "New Sub Category is inserted successfully.");
		}catch (Exception e) {
			e.printStackTrace();
			return new ToolOperationResponse("FAILED", "New Sub Category is inserted Failed."+e.getMessage());
		}
	}
}
