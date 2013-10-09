package com.mobile.tool.request.intr.dao;

import java.util.List;

import com.mobile.tool.request.intr.entity.RequestProcessorDimension;

public interface RequestProcessorDao {

	public RequestProcessorDimension fetchRequestProcessorByRequestType(String requestTypeName);

	public List<RequestProcessorDimension> fetchAllRequestProcessor();

	public void insertOrUpdate(
			RequestProcessorDimension requestProcessorDimension);
	
}
