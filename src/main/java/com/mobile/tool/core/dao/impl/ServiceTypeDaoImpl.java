package com.mobile.tool.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mobile.tool.core.dao.ServiceTypeDao;
import com.mobile.tool.core.entity.ServiceType;

@Repository
public class ServiceTypeDaoImpl implements ServiceTypeDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceType> fetchAll() {
		return hibernateTemplate.findByNamedQuery(ServiceType.FETCH_ALL);
	}

	@Override
	public void insertOrUpdate(ServiceType serviceType) {
		hibernateTemplate.saveOrUpdate(serviceType);
	}
}
