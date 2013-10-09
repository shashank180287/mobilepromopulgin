package com.mobile.tool.core.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.mobile.tool.core.datahandler.DBManager;
import com.mobile.tool.core.datahandler.DataStorage;
import com.mobile.tool.core.entity.ServiceType;

@Component
public class DataCacheLoader implements ApplicationContextAware {

	ApplicationContext applicationContext;

	@Autowired
	private DBManager dbManager;
	
	@PostConstruct
	public void startup() {
		System.out.println("Loading cache data...");
		List<ServiceType> serviceTypes = dbManager.fetchAllServiceTypes();
		System.out.println("Total Service Type are "+(serviceTypes!=null?serviceTypes.size():"NULL"));
		DataStorage.loadServiceTypes(serviceTypes);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
