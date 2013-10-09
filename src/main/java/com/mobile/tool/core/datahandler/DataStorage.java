package com.mobile.tool.core.datahandler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.map.MultiKeyMap;

import com.mobile.tool.core.entity.Location;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.promo.model.UserDetails;

public class DataStorage {

	private static Map<String, UserDetails> userDetailsByIdMap = new ConcurrentHashMap<String, UserDetails>();
	private static MultiKeyMap locationByLatLongMap = new MultiKeyMap(); 
	private static Map<String, ServiceType> serviceTypeByCodeMap = new ConcurrentHashMap<String, ServiceType>();
	private static Map<String, List<String>> listOfServiceTypeCodeByUserIdMap = new ConcurrentHashMap<String, List<String>>();
	
	public static void loadServiceTypes(List<ServiceType> serviceTypes) {
		for (ServiceType serviceType : serviceTypes) {
			serviceTypeByCodeMap.put(serviceType.getCode(), serviceType);
		}
	}
	
	public static UserDetails fetchUserDetailsByUserId(String userId) {
		if(userDetailsByIdMap.containsKey(userId))
			return userDetailsByIdMap.get(userId);
		return null;
	}
	
	public static Location fetchLocationByLatLong(double lat, double lon) {
		if(locationByLatLongMap.containsKey(lat, lon))
			return (Location) locationByLatLongMap.get(lat, lon);
		return null;
	}
	
	public static List<String> fetchServiceTypeCodesByUserPreferrence(String userId) {
		if(listOfServiceTypeCodeByUserIdMap.containsKey(userId))
			return listOfServiceTypeCodeByUserIdMap.get(userId);
		return null;
	}
	
	public static ServiceType fetchServiceTypeByCode(String servicetypeCode) {
		if(serviceTypeByCodeMap.containsKey(servicetypeCode))
			return serviceTypeByCodeMap.get(servicetypeCode);
		return null;
	}
	
	public static void storeUserDetailsInCache(UserDetails userDetails) {
		userDetailsByIdMap.put(userDetails.getUserId(), userDetails);
	}
	
	public static void storeLocationInCache(Location location) {
		locationByLatLongMap.put(location.getLatitude(), location.getLongitude(), location);
	}
	
	public static Collection<ServiceType> fetchAllServiceTypes() {
		return serviceTypeByCodeMap.values();
	}

	public static void storeServiceTypeCodesByUserPreferrence(String userId, String serviceTypeCodes) {
		listOfServiceTypeCodeByUserIdMap.put(userId, Arrays.asList(serviceTypeCodes.split(",")));
	}

	public static void addServiceTypeInCache(ServiceType serviceType) {
		serviceTypeByCodeMap.put(serviceType.getCode(), serviceType);
	}

}
