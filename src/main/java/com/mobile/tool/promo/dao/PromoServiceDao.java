package com.mobile.tool.promo.dao;

import java.util.List;

import com.mobile.tool.promo.entity.PromoService;

public interface PromoServiceDao {

	public void	insertPromoService(PromoService promoService);
	
	public List<PromoService> findServicesByLocationAndTime(double latitude, double longitude, double radius);
	
	public List<PromoService> findServicesByLocationTimeAndUserPreference(double latitude, double longitude, double radius, List<String> userPrefferedServiceTypes);
	
	public List<PromoService> findServicesByLocationTime(double latitude, double longitude, double radius);
}
