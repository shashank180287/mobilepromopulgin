package com.mobile.tool.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mobile.tool.core.dao.LocationDao;
import com.mobile.tool.core.entity.Location;

@Repository
public class LocationPostgresDaoImpl implements LocationDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void insertOrUpdateLocation(Location location) {
		hibernateTemplate.saveOrUpdate(location);
	}

}
