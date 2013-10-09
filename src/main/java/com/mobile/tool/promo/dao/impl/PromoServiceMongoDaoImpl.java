package com.mobile.tool.promo.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.geo.Circle;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.mobile.tool.promo.dao.PromoServiceDao;
import com.mobile.tool.promo.entity.PromoService;

@Repository
public class PromoServiceMongoDaoImpl implements PromoServiceDao{

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void insertPromoService(PromoService promoService) {
		mongoOperations.save(promoService);
	}

	@Override
	public List<PromoService> findServicesByLocationAndTime(double latitude, double longitude, double radius) {
		Circle circle = new Circle(latitude, longitude, radius);
		Criteria locationAndTimeCriteria = Criteria.where("location").withinCenterSphere(circle);
		Date today = new Date();
		String dayName = new SimpleDateFormat("EEE", Locale.ENGLISH).format(today).toUpperCase();
		locationAndTimeCriteria.and("validityStartDate").lte(today);
		locationAndTimeCriteria.and("validityEndDate").gte(today);
		locationAndTimeCriteria.and("applicableStartTime").lte(today);
		locationAndTimeCriteria.and("applicableEndTime").gte(today);
		locationAndTimeCriteria.and("applicableDays").regex("*"+dayName+"*");
		Query locationAndTimeQuery = new Query(locationAndTimeCriteria);
		return mongoOperations.find(locationAndTimeQuery, PromoService.class);
	}
	

	@Override
	public List<PromoService> findServicesByLocationTimeAndUserPreference(double latitude, double longitude, double radius, List<String> userPrefferedServiceTypes) {
		Circle circle = new Circle(latitude, longitude, radius);
		Criteria locationAndTimeCriteria = Criteria.where("location").withinCenterSphere(circle);
		Calendar today = Calendar.getInstance();
		String dayName = new SimpleDateFormat("EEE", Locale.ENGLISH).format(today.getTime()).toUpperCase();
		locationAndTimeCriteria.and("validityStartDate").lte(today.getTime());
		locationAndTimeCriteria.and("validityEndDate").gte(today.getTime());
		today.set(Calendar.DATE, 1);
		today.set(Calendar.MONTH, 0);
		today.set(Calendar.YEAR, 2013);
		Date now = today.getTime();
		locationAndTimeCriteria.and("applicableStartTime").lte(now);
		locationAndTimeCriteria.and("applicableEndTime").gte(now);
		locationAndTimeCriteria.and("applicableDays").regex(".*"+dayName+".*");
		Object[] array = new Object[userPrefferedServiceTypes.size()];
		userPrefferedServiceTypes.toArray(array);
		locationAndTimeCriteria.and("serviceType").in(array);
		Query locationAndTimeQuery = new Query(locationAndTimeCriteria);
		System.out.println(locationAndTimeQuery.getQueryObject().toString());
		return mongoOperations.find(locationAndTimeQuery, PromoService.class);
	}

	@Override
	public List<PromoService> findServicesByLocationTime(double latitude,
			double longitude, double radius) {
		Circle circle = new Circle(latitude, longitude, radius);
		Criteria locationAndTimeCriteria = Criteria.where("location").withinCenterSphere(circle);
		Calendar today = Calendar.getInstance();
		String dayName = new SimpleDateFormat("EEE", Locale.ENGLISH).format(today.getTime()).toUpperCase();
		locationAndTimeCriteria.and("validityStartDate").lte(today.getTime());
		locationAndTimeCriteria.and("validityEndDate").gte(today.getTime());
		today.set(Calendar.DATE, 1);
		today.set(Calendar.MONTH, 0);
		today.set(Calendar.YEAR, 2013);
		Date now = today.getTime();
		locationAndTimeCriteria.and("applicableStartTime").lte(now);
		locationAndTimeCriteria.and("applicableEndTime").gte(now);
		locationAndTimeCriteria.and("applicableDays").regex(".*"+dayName+".*");
		Query locationAndTimeQuery = new Query(locationAndTimeCriteria);
		System.out.println(locationAndTimeQuery.getQueryObject().toString());
		return mongoOperations.find(locationAndTimeQuery, PromoService.class);
	}
}
