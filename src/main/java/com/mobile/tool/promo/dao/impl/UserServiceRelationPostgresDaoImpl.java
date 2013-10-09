package com.mobile.tool.promo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mobile.tool.promo.dao.UserServiceRelationDao;
import com.mobile.tool.promo.entity.UserServiceRelation;

@Repository
public class UserServiceRelationPostgresDaoImpl implements UserServiceRelationDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void insertOrUpdate(UserServiceRelation userServiceRelation) {
		hibernateTemplate.saveOrUpdate(userServiceRelation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchOrderedServiceTypeCodesListByUserId(String userId) {
		return hibernateTemplate.findByNamedQueryAndNamedParam(UserServiceRelation.FETCH_SERVICE_CODE_ORDER_BY_COUNT, "userId", userId);
	}

}
