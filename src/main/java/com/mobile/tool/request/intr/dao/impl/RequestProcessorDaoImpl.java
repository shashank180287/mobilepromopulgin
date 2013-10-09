package com.mobile.tool.request.intr.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mobile.tool.request.intr.dao.RequestProcessorDao;
import com.mobile.tool.request.intr.entity.RequestProcessorDimension;

@Repository
public class RequestProcessorDaoImpl implements RequestProcessorDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public RequestProcessorDimension fetchRequestProcessorByRequestType(String requestTypeName) {
		List<RequestProcessorDimension> requestProcessorDimensions = hibernateTemplate.findByNamedQueryAndNamedParam(RequestProcessorDimension.FETCH_BY_REQUEST_TYPE, "requestTypeName", requestTypeName);
		if(!requestProcessorDimensions.isEmpty()){
			return requestProcessorDimensions.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RequestProcessorDimension> fetchAllRequestProcessor() {
		return hibernateTemplate.findByNamedQuery(RequestProcessorDimension.FETCH_ALL);
	}

	@Override
	public void insertOrUpdate(
			RequestProcessorDimension requestProcessorDimension) {
		hibernateTemplate.saveOrUpdate(requestProcessorDimension);
	}
}
