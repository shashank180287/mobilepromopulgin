package com.mobile.tool.core.dao;

import java.util.List;

import com.mobile.tool.core.entity.ServiceType;

public interface ServiceTypeDao {

	public List<ServiceType> fetchAll();

	public void insertOrUpdate(ServiceType serviceType);

}
