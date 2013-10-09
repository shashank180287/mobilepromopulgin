package com.mobile.tool.request.intr.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mobile.tool.request.intr.dao.UserRequestMeasureDao;
import com.mobile.tool.request.intr.entity.UserRequestMeasure;
@Repository
public class UserRequestMeasureDaoImpl implements UserRequestMeasureDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void insertOrUpdate(UserRequestMeasure userRequestMeasure) {
		hibernateTemplate.saveOrUpdate(userRequestMeasure);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRequestMeasure> fetchAllUserRequestsForProcessing() {
		return hibernateTemplate.findByNamedQuery(UserRequestMeasure.FETCH_ALL_APPLICABLE_REQUEST);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserRequestMeasure fetchProcessedUserRequestMeasureByUserId(String userId) {
		List<UserRequestMeasure> userRequestMeasures = hibernateTemplate.findByNamedQueryAndNamedParam(UserRequestMeasure.FETCH_PROCESSED_USER_REQUESTS, "userId", userId);
		if(!userRequestMeasures.isEmpty()){
			return userRequestMeasures.get(0);
		}
		return null;
	}

}
