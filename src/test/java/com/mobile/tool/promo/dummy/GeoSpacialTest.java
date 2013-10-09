package com.mobile.tool.promo.dummy;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.geo.Circle;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;

public class GeoSpacialTest {

    public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/mobile/tool/promo/entity/promo-plugin-test.xml");
	
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
		Query query = new Query(Criteria.where("loc").withinCenterSphere(new Circle(12.913803, 77.599609, 0.001)));
		List<GeoPoint> list = mongoOperation.find(query, GeoPoint.class);
		System.out.println("size = " + list.size());

    }
    
}
