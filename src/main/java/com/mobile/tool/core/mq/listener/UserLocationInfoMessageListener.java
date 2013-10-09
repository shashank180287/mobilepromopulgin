package com.mobile.tool.core.mq.listener;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mobile.tool.core.datahandler.DBManager;
import com.mobile.tool.promo.model.UserDetails;

@Component
public class UserLocationInfoMessageListener implements MessageListeners {

	@Autowired
	private DBManager dbManager;
	
	@Override
	public void handleMeesage(String messageBody) throws JSONException {
		UserDetails userDetails = new UserDetails(new JSONObject(messageBody));
		dbManager.updateUserLocation(userDetails.getUserId(), userDetails.getCurrentLocation());
	}

}
