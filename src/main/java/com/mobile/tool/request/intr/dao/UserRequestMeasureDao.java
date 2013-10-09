package com.mobile.tool.request.intr.dao;

import java.util.List;

import com.mobile.tool.request.intr.entity.UserRequestMeasure;

public interface UserRequestMeasureDao {

	public void insertOrUpdate(UserRequestMeasure userRequestMeasure);

	public List<UserRequestMeasure> fetchAllUserRequestsForProcessing();

	public UserRequestMeasure fetchProcessedUserRequestMeasureByUserId(String userId);
	
}
