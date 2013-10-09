package com.mobile.tool.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mobile.tool.core.dao.UserSettingsDao;
import com.mobile.tool.core.entity.UserSettingsMeasure;

@Repository
public class UserSettingDaoImpl implements UserSettingsDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public UserSettingsMeasure fetchUserSettingsForUser(String userId) {
		List<UserSettingsMeasure> userSettingsMeasures = hibernateTemplate.findByNamedQueryAndNamedParam(UserSettingsMeasure.FETCH_BY_USER_ID, "userId", userId);
		if(!userSettingsMeasures.isEmpty()){
			return userSettingsMeasures.get(0);
		}
		return null;
	}

	@Override
	public void insertOrUpdate(UserSettingsMeasure userSettingsMeasure) {
		hibernateTemplate.saveOrUpdate(userSettingsMeasure);
	}

}
