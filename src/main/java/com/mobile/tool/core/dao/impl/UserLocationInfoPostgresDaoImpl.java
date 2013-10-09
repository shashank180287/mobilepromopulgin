package com.mobile.tool.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mobile.tool.core.dao.UserLocationInfoDao;
import com.mobile.tool.core.entity.UserLocationInfo;

@Repository
public class UserLocationInfoPostgresDaoImpl implements UserLocationInfoDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void insertOrUpdate(UserLocationInfo userLocationInfo) {
		hibernateTemplate.saveOrUpdate(userLocationInfo);
	}

}
