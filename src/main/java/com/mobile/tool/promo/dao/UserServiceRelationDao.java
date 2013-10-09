package com.mobile.tool.promo.dao;

import java.util.List;

import com.mobile.tool.promo.entity.UserServiceRelation;

public interface UserServiceRelationDao {

	public void insertOrUpdate(UserServiceRelation userServiceRelation);
	
	public List<String> fetchOrderedServiceTypeCodesListByUserId(String userId);
}
