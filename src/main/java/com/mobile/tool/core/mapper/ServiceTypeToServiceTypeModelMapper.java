package com.mobile.tool.core.mapper;

import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.model.ServiceTypeModel;

public class ServiceTypeToServiceTypeModelMapper {

	public static ServiceTypeModel	mapServiceTypeEntityToModel(ServiceType serviceType) {
		return new ServiceTypeModel(serviceType.getCode(), serviceType.getName());
	}
}
