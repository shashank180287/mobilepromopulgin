package com.mobile.tool.promo.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.tool.core.entity.Location;
import com.mobile.tool.core.service.LocationService;
import com.mobile.tool.core.service.UserPreferenceService;
import com.mobile.tool.promo.entity.PromoService;
import com.mobile.tool.promo.model.UserDetails;
import com.mobile.tool.promo.service.PromoServiceFinder;
import com.mobile.tool.promo.service.UserDetailsService;

@Controller
public class PromoToolController {

	@Autowired
	private PromoServiceFinder promoServiceFinder;
	@Autowired
	private UserPreferenceService userPreferenceService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@ModelAttribute("userdetail")
	public UserDetails mapRequestParamWithUserDetail(@RequestParam String id, @RequestParam double lat, @RequestParam double lon){
		UserDetails userDetails = userDetailsService.findUserDetailsByUserId(id);
		if(userDetails==null)
			userDetails = new UserDetails(id);
		Location location = locationService.findLocationByLatLong(lat, lon);
		userDetails.setCurrentLocation(location);
		userDetailsService.storeUserDetails(userDetails);
		return userDetails;
	}
	
	
	@RequestMapping(value="/services", method= RequestMethod.GET)
	public @ResponseBody String fetchServicesForUserByLocation(@ModelAttribute("userdetail") UserDetails userDetails){
		JSONArray serviceByUserMovementAndLocation = new JSONArray();
		try{
			List<String> userPreferredServiceTypeNames = userPreferenceService.fetchServiceTypeCodesByUserPreferrence(userDetails);
			List<PromoService> promoServiceByUserLocationAndTime = promoServiceFinder.findServicesByLocationTimeAndUserPreference(userDetails.getCurrentLocation(), userPreferredServiceTypeNames);
			promoServiceByUserLocationAndTime = userPreferenceService.sortServiceListWithUserPreference(userDetails.getUserId(), promoServiceByUserLocationAndTime);
			if(promoServiceByUserLocationAndTime.size()>0){
				JSONObject object = new JSONObject();
				object.put(userDetails.getCurrentLocation().generateLocationCode(), prepareResponseFromServiceList(promoServiceByUserLocationAndTime).toString());
				serviceByUserMovementAndLocation.put(object);
			}
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return serviceByUserMovementAndLocation.toString();
	}

	private JSONArray prepareResponseFromServiceList(List<PromoService> promoServiceByUserLocationAndTime) throws JSONException {
		JSONArray jsonArray = new JSONArray();
		for (PromoService promoService : promoServiceByUserLocationAndTime) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", promoService.getName());
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:SS");
			jsonObject.put("time", sdf.format(promoService.getApplicableStartTime())+"-"+sdf.format(promoService.getApplicableEndTime()));
			jsonObject.put("message", promoService.getServiceMessage());
			jsonObject.put("type", promoService.getBusinessType());
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

}
