package com.mobile.tool.core.mq.listener;

import org.json.JSONException;

public interface MessageListeners {

	public void handleMeesage(String messageBody) throws JSONException;
	
}
