package com.mobile.tool.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.tool.core.request.model.UserSettingsChangeRequest;
import com.mobile.tool.core.response.model.UserSettingsChangeResponse;
import com.mobile.tool.core.service.UserPreferenceService;

@Controller
@RequestMapping(value="/userdetails")
public class UserDetailsController {

	@Autowired
	private UserPreferenceService userPreferenceService;
	
	@RequestMapping(value="/settings", method= RequestMethod.PUT)
	public @ResponseBody UserSettingsChangeResponse changeUserSettings(@RequestBody UserSettingsChangeRequest userSettingsChangeRequest){
		try{
			userPreferenceService.updateUserPreferrences(userSettingsChangeRequest.getUserId(), userSettingsChangeRequest.getUserSelectedServiceTypes());
			return new UserSettingsChangeResponse("SUCCESS", "User settings are changed successfully.");
		}catch (Exception e) {
			e.printStackTrace();
			return new UserSettingsChangeResponse("FAILED", e.getMessage());
		}
		
	}
}
