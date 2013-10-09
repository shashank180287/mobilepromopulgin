package com.mobile.tool.request.intr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.request.intr.entity.RequestProcessorDimension;

@Service
public class RequestProcessorSevice {

	@Autowired
	private DataHandler dataHandler;
	
	public void insertRequestProcessor(String requestType, String processorClass, Integer noOfContexts, Integer noOfSubContexts) {
		RequestProcessorDimension requestProcessorDimension = new RequestProcessorDimension(requestType, processorClass, noOfContexts, noOfSubContexts);
		dataHandler.insertOrUpdateRequestProcessorDimension(requestProcessorDimension);
	}

}
