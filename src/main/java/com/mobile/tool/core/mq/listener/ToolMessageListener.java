package com.mobile.tool.core.mq.listener;

import static com.mobile.tool.core.util.Constant.*;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component("promoPluginMessageListener")
public class ToolMessageListener {

	@Autowired
	private ApplicationContext applicationContext;
	
	public void onMessage(Map<String, Object> message) throws Exception {
    	String msgListener= (String) message.get(AMQ_MESSAGE_LISTENER_FIELD_NAME);
    	String messageBody = (String) message.get(AMQ_MESSAGE_FIELD_NAME);
    	MessageListeners messageListeners = (MessageListeners) applicationContext.getBean(msgListener);
    	messageListeners.handleMeesage(messageBody);
	}

}
