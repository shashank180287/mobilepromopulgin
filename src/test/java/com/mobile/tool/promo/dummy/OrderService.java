package com.mobile.tool.promo.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    static int orderSequence = 1;
    
    @Autowired
    private OrderSender orderSender;
     
    public void sendOrder(int customerId, double price)
    {
        Order order = new Order(orderSequence, 2, price, "ordercd"+ orderSequence++);
        orderSender.sendOrder(order);
    }
    
}
