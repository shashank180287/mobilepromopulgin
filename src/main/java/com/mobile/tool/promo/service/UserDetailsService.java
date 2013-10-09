package com.mobile.tool.promo.service;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.promo.model.UserDetails;

@Service
public class UserDetailsService {

	@Autowired
	private DataHandler dataHandler;
	
	public UserDetails findUserDetailsByUserId(String userId) {
		return dataHandler.fetchUserDetailsByUserId(userId);
	}

	public void storeUserDetails(UserDetails userDetails) {
		dataHandler.storeUserDetailsInCacheAndReportDb(userDetails);
	}

	public void storeUserLastVisitInformationInReportDb(String userId, String userLastVisitsInfo) throws JSONException {
		dataHandler.storeUserLastVisitInformationInReportDb(userId, userLastVisitsInfo);
	}

}
