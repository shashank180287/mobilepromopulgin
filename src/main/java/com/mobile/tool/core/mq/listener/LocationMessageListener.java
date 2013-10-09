package com.mobile.tool.core.mq.listener;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mobile.tool.core.datahandler.DBManager;
import com.mobile.tool.core.entity.Location;

@Component
public class LocationMessageListener implements MessageListeners{

	@Autowired
	private DBManager dbManager;
	
	@Override
	public void handleMeesage(String messageBody) throws JSONException {
		Location location = new Location(new JSONObject(messageBody));
		dbManager.insertOrUpdateLocation(location);
	}

}
