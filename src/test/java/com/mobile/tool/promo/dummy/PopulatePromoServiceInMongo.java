package com.mobile.tool.promo.dummy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;

import com.mobile.tool.promo.entity.PromoService;

public class PopulatePromoServiceInMongo {

	public static void main(String[] args) {

    	ApplicationContext ctx = new ClassPathXmlApplicationContext("com/mobile/tool/promo/dummy/promo-plugin-test.xml");

    	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    	
    	Date startDate = new Date(new DateTime(2013, 8, 30, 10, 0).getMillis());
    	Date endDate = new Date(new DateTime(2013, 12, 31, 10, 0).getMillis());
    	Date startTime = new Date(new DateTime(2013, 1, 1, 3, 0, 0).getMillis());
    	Date endTime = new Date(new DateTime(2013, 1, 1, 23, 0, 0).getMillis());
    	List<String> applicableDays = new ArrayList<String>();
    	applicableDays.add("TUE");
    	applicableDays.add("THU");
    	applicableDays.add("SAT");    	
    	applicableDays.add("SUN");
    	PromoService promoService  = new PromoService("service1", "G", "service number 1", startDate, endDate, startTime, endTime, applicableDays, new double[]{12.916460, 77.610008}, "bt", false);
    	mongoOperation.save(promoService);

    	startTime = new Date(new DateTime(2013, 1, 1, 12, 0, 0).getMillis());
    	endTime = new Date(new DateTime(2013, 1, 1, 21, 0, 0).getMillis());
    	applicableDays = new ArrayList<String>();
    	applicableDays.add("TUE");
    	applicableDays.add("WED");
    	promoService  = new PromoService("service6", "A", "service number 6", startDate, endDate, startTime, endTime, applicableDays, new double[]{12.913803, 77.599609}, "bt", true);
    	mongoOperation.save(promoService);
    	
    	startTime = new Date(new DateTime(2013, 1, 1, 14, 0, 0).getMillis());
    	endTime = new Date(new DateTime(2013, 1, 1, 20, 0, 0).getMillis());
    	applicableDays = new ArrayList<String>();
    	applicableDays.add("TUE");
    	applicableDays.add("WED");
    	promoService  = new PromoService("service7", "O", "service number 7", startDate, endDate, startTime, endTime, applicableDays, new double[]{16.913803, 71.599609}, "bt", false);
    	mongoOperation.save(promoService);
    	
    	List<PromoService> listPromoService = mongoOperation.getCollection(PromoService.class);
    	System.out.println("size of employees = " + listPromoService.size());

    }
}
