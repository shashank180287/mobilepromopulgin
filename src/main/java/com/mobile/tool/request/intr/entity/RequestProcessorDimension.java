package com.mobile.tool.request.intr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name=RequestProcessorDimension.FETCH_BY_REQUEST_TYPE, query="select obj from RequestProcessorDimension obj where obj.requestType= :requestTypeName"),
		@NamedQuery(name=RequestProcessorDimension.FETCH_ALL, query="from RequestProcessorDimension obj")
})
@Table(name="request_processor_dimension")
public class RequestProcessorDimension {

	public static final String FETCH_BY_REQUEST_TYPE = "fecthRequestProcessorByType";
	public static final String FETCH_ALL = "fecthAllRequestProcessor";
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int processorId;
	
	@Column(name="request_type")
	private String requestType;
	
	@Column(name="processor_class")
	private String processorClassName;
	
	@Column(name="no_of_contexts")
	private Integer noOfContexts;
	
	@Column(name="no_of_sub_contexts")
	private Integer noOfSubContexts;

	public RequestProcessorDimension() {
		super();
	}
	
	public RequestProcessorDimension(String requestType,
			String processorClassName, Integer noOfContexts,
			Integer noOfSubContexts) {
		super();
		this.requestType = requestType;
		this.processorClassName = processorClassName;
		this.noOfContexts = noOfContexts;
		this.noOfSubContexts = noOfSubContexts;
	}

	public int getProcessorId() {
		return processorId;
	}

	public void setProcessorId(int processorId) {
		this.processorId = processorId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getProcessorClassName() {
		return processorClassName;
	}

	public void setProcessorClassName(String processorClassName) {
		this.processorClassName = processorClassName;
	}

	public Integer getNoOfContexts() {
		return noOfContexts;
	}

	public void setNoOfContexts(Integer noOfContexts) {
		this.noOfContexts = noOfContexts;
	}

	public Integer getNoOfSubContexts() {
		return noOfSubContexts;
	}

	public void setNoOfSubContexts(Integer noOfSubContexts) {
		this.noOfSubContexts = noOfSubContexts;
	}

}
