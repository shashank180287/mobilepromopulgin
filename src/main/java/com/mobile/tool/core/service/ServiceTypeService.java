package com.mobile.tool.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.model.ServiceTypeModel;
import com.mobile.tool.core.response.model.ServiceTypesResponse;

@Service
public class ServiceTypeService {

	@Autowired
	private DataHandler dataHandler;
	
	public ServiceTypesResponse fetchAllServiceTypes() {
		Collection<ServiceType> serviceTypes = dataHandler.fetchAllServiceTypes();
		List<ServiceTypeModel> serviceTypeModelList = new ArrayList<ServiceTypeModel>();
		for (ServiceType serviceType : serviceTypes) {
			serviceTypeModelList.add(new ServiceTypeModel(serviceType.getCode(), serviceType.getName()));
		}
		ServiceTypeModel[] serviceTypeModels = new ServiceTypeModel[serviceTypeModelList.size()];
		serviceTypeModelList.toArray(serviceTypeModels);
		return new ServiceTypesResponse(serviceTypeModels);
	}

	public void insertServiceType(String code, String name) {
		ServiceType serviceType = new ServiceType(name, code);
		dataHandler.addServiceTypeInReportDBandCache(serviceType);
	}

}
