package com.mobile.tool.core.dao;

import com.mobile.tool.core.entity.UserSettingsMeasure;

public interface UserSettingsDao {

	public UserSettingsMeasure fetchUserSettingsForUser(String userId);

	public void insertOrUpdate(UserSettingsMeasure userSettingsMeasure);
	
}
