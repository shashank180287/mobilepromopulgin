package com.mobile.tool.core.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.tool.core.model.ServiceTypeModel;
import com.mobile.tool.core.response.model.ServiceTypesResponse;
import com.mobile.tool.core.response.model.ToolOperationResponse;
import com.mobile.tool.core.service.ServiceTypeService;

@Controller
@RequestMapping(value="/service")
public class ServiceTypeController {

	@Autowired
	private ServiceTypeService serviceTypeService;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public @ResponseBody ServiceTypesResponse fetchAllServiceTypes(HttpServletRequest req){
		Enumeration<String> paramNames =req.getHeaderNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			System.out.println("REQPARAMWRITER>>>TEST>>>PARAM_NAME="+paramName+"&PARAM_VALUE="+req.getHeader(paramName));
		}
		return serviceTypeService.fetchAllServiceTypes();
	}
	
	@RequestMapping(value="/", method= RequestMethod.POST)
	public @ResponseBody ToolOperationResponse addServiceType(@RequestBody ServiceTypeModel serviceTypeModel){
		try{
			serviceTypeService.insertServiceType(serviceTypeModel.getCode(), serviceTypeModel.getName());
			return new ToolOperationResponse("SUCCESS", "New Service Type is inserted successfully.");
		}catch (Exception e) {
			e.printStackTrace();
			return new ToolOperationResponse("FAILED", "New Service Type is inserted Failed."+e.getMessage());
		}
	}
}
