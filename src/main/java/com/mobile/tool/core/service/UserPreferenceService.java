 package com.mobile.tool.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.UserSettingsMeasure;
import com.mobile.tool.promo.entity.PromoService;
import com.mobile.tool.promo.model.UserDetails;

@Service
public class UserPreferenceService {

	@Autowired
	private DataHandler dataHandler;
	
	@SuppressWarnings("unchecked")
	public List<String> fetchServiceTypeCodesByUserPreferrence(UserDetails userDetails) {
		List<ServiceType> serviceTypesForUser = dataHandler.fetchServiceTypeCodesByUserPreferrence(userDetails);
		List<String> serviceTypeCodes = (List<String>) CollectionUtils.collect(serviceTypesForUser, new Transformer() {
			
			@Override
			public Object transform(Object serviceType) {
				if(serviceType instanceof ServiceType)
					return ((ServiceType) serviceType).getCode();
				return null;
			}
		});
		return serviceTypeCodes;
	}

	public List<ServiceType> updateUserServiceTypePreferrenceWithNewServiceType(String userId) {
		Collection<ServiceType> serviceTypes = dataHandler.fetchAllServiceTypes();
		UserSettingsMeasure userSettingsMeasure = dataHandler.fetchUserSettingsMeasureForUser(userId);
		List<String> userServiceType = Arrays.asList(userSettingsMeasure.getServiceTypeCodes());
		List<ServiceType> newlyAddedServiceTypeCode = new ArrayList<>();
		for (ServiceType serviceType : serviceTypes) {
			if(serviceType.getCreatedAt().after(userSettingsMeasure.getModifiedAt())){
				newlyAddedServiceTypeCode.add(serviceType);
				userServiceType.add(serviceType.getCode());
			}
		}
		if(!newlyAddedServiceTypeCode.isEmpty()){
			dataHandler.updateUserPreferrencesByUserId(userId, userServiceType.toString());
			return newlyAddedServiceTypeCode;
		}
		return null;
	}
	
	public void updateUserPreferrences(String userId, String userSelectedServiceTypeCodes) {
		dataHandler.updateUserPreferrencesByUserId(userId, userSelectedServiceTypeCodes);
	}

	public List<PromoService> sortServiceListWithUserPreference(String userId, List<PromoService> promoServiceByUserLocationAndTime) {
		final List<String> orderedServiceTypeCodeList = dataHandler.fetchOrderedServiceTypeCodesListAsUserPreferrence(userId);
		Collections.sort(promoServiceByUserLocationAndTime, new Comparator<PromoService>() {
			@Override
			public int compare(PromoService service1, PromoService service2) {
				if(orderedServiceTypeCodeList.contains(service1.getServiceType()) && orderedServiceTypeCodeList.contains(service2.getServiceType())){
					Integer serviceIdx1 = new Integer(orderedServiceTypeCodeList.indexOf(service1.getServiceType()));
					Integer serviceIdx2 = new Integer(orderedServiceTypeCodeList.indexOf(service2.getServiceType()));
					if(serviceIdx1!=null && serviceIdx2!=null && serviceIdx1.intValue()==serviceIdx2.intValue()){
						if(service1.isPremium()==true && service2.isPremium()==false){
							return -1;
						}else if(service1.isPremium()==false && service2.isPremium()==true){
							return 1;
						}
					}
					return serviceIdx1.compareTo(serviceIdx2);
				}else if(orderedServiceTypeCodeList.contains(service1.getServiceType()))
					return -1;
				else if(orderedServiceTypeCodeList.contains(service2.getServiceType()))
					return 1;
				else
					return service1.getServiceType().compareTo(service2.getServiceType());
			}
		});
		return promoServiceByUserLocationAndTime;
	}

}
