package com.mobile.tool.promo.dummy;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;

/**
 * Java Spring Data & MongoDB Example
 * 
 */
public class SpringDataMongoApp {

    public static void main(String[] args) {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("com/mobile/tool/promo/entity/promo-plugin-test.xml");

	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	EmployeeTest employee = new EmployeeTest("100", "firstName", "lastName", 23);

	// save
	mongoOperation.save("employees", employee);

	// find
	EmployeeTest savedEmployee = mongoOperation.findOne("employees", new Query(Criteria.where(
		"id").is("100")), EmployeeTest.class);

	System.out.println("Saved Employee: " + savedEmployee);

	// update
	mongoOperation.updateFirst("employees", new Query(Criteria.where(
		"firstname").is("firstName")), Update.update("lastname",
		"new lastName"));

	// find
	EmployeeTest updatedEmployee = mongoOperation.findOne("employees", new Query(
		Criteria.where("id").is("100")), EmployeeTest.class);

	System.out.println("Updated Employee: " + updatedEmployee);

	// delete
	mongoOperation.remove("employees", new Query(Criteria.where("id").is(
		"100")), EmployeeTest.class);

	// List
	List<EmployeeTest> listEmployee = mongoOperation.getCollection("employees", EmployeeTest.class);
	System.out.println("size of employees = " + listEmployee.size());

    }
}
