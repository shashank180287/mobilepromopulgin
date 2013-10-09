package com.mobile.tool.promo.dummy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJms {

    public static void main(String[] args) {
        
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("com/mobile/tool/promo/dummy/promo-plugin-test.xml");
    	
    	OrderService orderService = (OrderService) ctx.getBean("orderService");
        
        for(int i =1; i<=5; i++)
            orderService.sendOrder(1+i, 10.0D+i);
    }
    
    
}
