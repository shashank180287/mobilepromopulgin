package com.mobile.tool.promo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.tool.core.entity.Location;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.mapper.ServiceTypeToServiceTypeModelMapper;
import com.mobile.tool.core.model.ServiceTypeModel;
import com.mobile.tool.core.response.model.ServiceTypesResponse;
import com.mobile.tool.core.service.LocationService;
import com.mobile.tool.core.service.UserPreferenceService;
import com.mobile.tool.inventory.mapper.InventorySearchResponseMapper;
import com.mobile.tool.inventory.model.Item;
import com.mobile.tool.promo.entity.PromoService;
import com.mobile.tool.promo.model.UserDetails;
import com.mobile.tool.promo.response.model.PromoToolStatusResponse;
import com.mobile.tool.promo.service.PromoServiceFinder;
import com.mobile.tool.promo.service.UserDetailsService;
import com.mobile.tool.request.intr.entity.UserRequestMeasure;
import com.mobile.tool.request.intr.service.UserRequestIntrService;

@Controller
@RequestMapping(value="/tracker")
public class ToolTrackingController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PromoServiceFinder promoServiceFinder;
	@Autowired
	private UserPreferenceService userPreferenceService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private UserRequestIntrService userRequestIntrService;
	
	public UserDetails mapRequestParamWithUserDetail(String id, double lat, double lon){
		UserDetails userDetails = userDetailsService.findUserDetailsByUserId(id);
		if(userDetails==null)
			userDetails = new UserDetails(id);
		Location location = locationService.findLocationByLatLong(lat, lon);
		userDetails.setCurrentLocation(location);
		userDetailsService.storeUserDetails(userDetails);
		return userDetails;
	}
	

	@RequestMapping(value="/status", method=RequestMethod.GET)
	public @ResponseBody PromoToolStatusResponse checkUserToolUpdationStatus(@RequestParam String id, @RequestParam double lat, @RequestParam double lon){
		UserDetails userDetails = mapRequestParamWithUserDetail(id, lat, lon);
		
		PromoToolStatusResponse statusResp = new PromoToolStatusResponse();
		try{
			List<ServiceType> newlyUpdatedServiceTypes = userPreferenceService.updateUserServiceTypePreferrenceWithNewServiceType(userDetails.getUserId());
			if(newlyUpdatedServiceTypes!=null && !newlyUpdatedServiceTypes.isEmpty()){
				List<ServiceTypeModel> serviceTypeModels = new ArrayList<>();
				for (ServiceType serviceType : newlyUpdatedServiceTypes) {
					serviceTypeModels.add(ServiceTypeToServiceTypeModelMapper.mapServiceTypeEntityToModel(serviceType));
				}
				ServiceTypeModel[] serviceTypeModelArray = new ServiceTypeModel[serviceTypeModels.size()];
				serviceTypeModels.toArray(serviceTypeModelArray);
				statusResp.withServiceTypesResponse(new ServiceTypesResponse(serviceTypeModelArray));
			}
			
			List<String> userPreferredServiceTypeNames = userPreferenceService.fetchServiceTypeCodesByUserPreferrence(userDetails);
			List<PromoService> promoServiceByUserLocationAndTime = promoServiceFinder.findServicesByLocationTimeAndUserPreference(userDetails.getCurrentLocation(), userPreferredServiceTypeNames);
			if(promoServiceByUserLocationAndTime.size()>0){
				statusResp.withServiceAvailableFlag('Y');
			}else{
				statusResp.withServiceAvailableFlag('N');
			}
			
			UserRequestMeasure userRequestMeasure = userRequestIntrService.getUserRequestMeasureForUser(userDetails.getUserId());
			if(userRequestMeasure!=null){
				List<Item> itemBasedOnUserRequest = userRequestIntrService.fetchItemsForUserRequestMeasure(userRequestMeasure);
				if(itemBasedOnUserRequest!=null && !itemBasedOnUserRequest.isEmpty()){
					String serviceType = itemBasedOnUserRequest.get(0).getServiceTypeCode();
					String subCategoryName = itemBasedOnUserRequest.get(0).getSubCategoryName();
					statusResp.withInventorySearchResponse(InventorySearchResponseMapper.mapToInventorySearchResponse(itemBasedOnUserRequest, serviceType, subCategoryName));
				}
				userRequestMeasure.setResponseSendToUser(true);
				userRequestMeasure.setModifiedAt(new Date(new java.util.Date().getTime()));
				userRequestIntrService.insertOrUpdateUserRequest(userRequestMeasure);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return statusResp;
	}
	
	
	@RequestMapping(value="/{userId}/visits", method=RequestMethod.POST)
	public @ResponseBody String trackUserToolVisits(@PathVariable String userId, @RequestBody String userVisits){
		try{
			if(StringUtils.hasText(userVisits)){
				userDetailsService.storeUserLastVisitInformationInReportDb(userId, userVisits);
			}
			return "SUCCESS";
		}catch (Exception e) {
			e.printStackTrace();
			return "FAILED";
		}
	}
}
