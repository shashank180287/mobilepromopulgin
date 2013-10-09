package com.mobile.tool.promo.mq.listener;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mobile.tool.core.datahandler.DBManager;
import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.mq.listener.MessageListeners;
import com.mobile.tool.core.util.Utilities;
import com.mobile.tool.promo.entity.UserServiceRelation;
@Component
public class UserVisitInfoListener implements MessageListeners {

	@Autowired
	private DBManager dbManager;
	@Autowired
	private DataHandler dataHandler;
	
	@Override
	public void handleMeesage(String messageBody) throws JSONException {
		JSONObject userVisitInfosById = new JSONObject(messageBody);
		String userId = userVisitInfosById.getString("userId");
		JSONArray userVisitInfosArray = userVisitInfosById.getJSONArray("userVisitInfos");
		for (int i = 0; i < userVisitInfosArray.length(); i++) {
			JSONObject userVisitInfo = (JSONObject) userVisitInfosArray.get(i);
			UserServiceRelation userServiceRelation = convertJsonObjectToDbObject(userVisitInfo, userId);
			if(userServiceRelation!=null)
				dbManager.insertOrUpdateUserServiceRelation(userServiceRelation);
		}
	}

	private UserServiceRelation convertJsonObjectToDbObject(JSONObject userVisitInfo, String userId) throws JSONException {
		if(userVisitInfo.has("service") && userVisitInfo.has("serviceType") && userVisitInfo.has("dateTime")){
			UserServiceRelation userServiceRelation = new UserServiceRelation();
			userServiceRelation.setService(userVisitInfo.getString("service"));
			userServiceRelation.setServiceType(dataHandler.fetchServiceTypeByCode(userVisitInfo.getString("serviceType")));
			try {
				userServiceRelation.setVisitedTS(new Timestamp(Utilities.convertStringToDate(userVisitInfo.getString("dateTime"),"yyyy-MM-dd HH:mm:ss").getTime()));
			} catch (ParseException e) {
				userServiceRelation.setVisitedTS(new Timestamp(new Date().getTime()));
			}
			userServiceRelation.setUserId(userId);
			return userServiceRelation;
		}
		return null;
	}

}
