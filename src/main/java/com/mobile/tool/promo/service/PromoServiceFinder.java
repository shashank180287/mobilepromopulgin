package com.mobile.tool.promo.service;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static java.util.Calendar.MINUTE;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.isIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.entity.Location;
import com.mobile.tool.promo.entity.PromoService;
import com.mobile.tool.promo.utility.PromoConstant;

@Service
public class PromoServiceFinder {

	@Autowired
	private DataHandler dataHandler;
	
	public List<PromoService> findServicesByLocationTimeAndUserPreference(Location location, List<String> userPreferredServiceTypeCodes) {
		Calendar now = Calendar.getInstance();
		int minute = now.get(MINUTE);
		minute -=(minute%10);
		now.set(MINUTE, minute);
		return getServicesByLocationAndUserPreferrence(location.getLatitude(), location.getLongitude(), userPreferredServiceTypeCodes, now.getTime());
	}

	@Cacheable(cacheName="services_by_user_location")
	private List<PromoService> getServicesByLocationAndUserPreferrence(double latitude, double longitude, List<String> userPreferredServiceTypeCodes, Date time) {
		List<PromoService> promoServices = getServicesByLocation(latitude, longitude, time);
		return select(promoServices, having(on(PromoService.class).getServiceType(), isIn(userPreferredServiceTypeCodes)));
	}

	@Cacheable(cacheName="services_by_location")
	private List<PromoService> getServicesByLocation(double latitude, double longitude, Date timeTillMinute){
		return dataHandler.fetchServicesByLocationAndTime(latitude, longitude, PromoConstant.SERVICE_CIRCLE_RADIUS, timeTillMinute);
	}
}
