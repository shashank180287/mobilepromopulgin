package com.mobile.tool.promo.dummy;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageProducer {

	   private static final Logger logger = Logger.getLogger(JmsMessageProducer.class);

	    protected static final String MESSAGE_COUNT = "messageCount";

	    @Autowired
	    private JmsTemplate template = null;
	    private int messageCount = 100;

	    /**
	     * Generates JMS messages
	     */
	    @PostConstruct
	    public void generateMessages() throws JMSException {
	        for (int i = 0; i < messageCount; i++) {
	            final int index = i;
	            final String text = "Message number is " + i + ".";

	            template.send(new MessageCreator() {
	                public Message createMessage(Session session) throws JMSException {
	                    TextMessage message = session.createTextMessage(text);
	                    message.setIntProperty(MESSAGE_COUNT, index);
	                    
	                    logger.info("Sending message: " + text);
	                    
	                    return message;
	                }
	            });
	        }
	    }
}
