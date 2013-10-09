package com.mobile.tool.request.intr.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;

@Entity
@NamedQueries({
	@NamedQuery(name=UserRequestMeasure.FETCH_ALL_APPLICABLE_REQUEST, query="from UserRequestMeasure obj where obj.requestCompleted=false"),
	@NamedQuery(name=UserRequestMeasure.FETCH_PROCESSED_USER_REQUESTS, query="from UserRequestMeasure obj where obj.requestCompleted=true and obj.responseSendToUser=false and obj.userId= :userId")
})
@Table(name="user_request_measure")
public class UserRequestMeasure {

	public static final String FETCH_ALL_APPLICABLE_REQUEST="fetchAllRequestForProcessing";
	public static final String FETCH_PROCESSED_USER_REQUESTS="fetchProcessedRequest";
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;
	
	@Column(name="user_id")
	private String userId;
	
	@ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
	private ServiceType serviceType;
	
	@ManyToOne
    @JoinColumn(name = "sub_cat_id", nullable = false)
	private SubCategoryDimension subCategoryDimension;
	
	@ManyToOne
    @JoinColumn(name = "request_processor_id", nullable = false)
	private RequestProcessorDimension requestProcessorDimension;

	@Column(name="primary_context")
	private String primaryContext;
	
	@Column(name="secondary_context")
	private String secondaryContext;
	
	@Column(name="sub_contexts")
	private String subContexts;

	@Column(name="created_ts")
	private Date createdAt;
	
	@Column(name="modified_ts")
	private Date modifiedAt;
	
	@Column(name="is_completed")
	private boolean requestCompleted;
	
	@Column(name="send_to_user")
	private boolean responseSendToUser;
	
	@Column(name="resulted_items")
	private String responseItemsCode;
	
	public UserRequestMeasure() {
		super();
	}

	public UserRequestMeasure(String userId, ServiceType serviceType,
			SubCategoryDimension subCategoryDimension,
			RequestProcessorDimension requestProcessorDimension,
			String primaryContext, String secondaryContext, String subContexts,
			Date createdAt) {
		super();
		this.userId = userId;
		this.serviceType = serviceType;
		this.subCategoryDimension = subCategoryDimension;
		this.requestProcessorDimension = requestProcessorDimension;
		this.primaryContext = primaryContext;
		this.secondaryContext = secondaryContext;
		this.subContexts = subContexts;
		this.createdAt = createdAt;
		this.requestCompleted = false;
		this.responseSendToUser = false;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public SubCategoryDimension getSubCategoryDimension() {
		return subCategoryDimension;
	}

	public void setSubCategoryDimension(SubCategoryDimension subCategoryDimension) {
		this.subCategoryDimension = subCategoryDimension;
	}

	public RequestProcessorDimension getRequestProcessorDimension() {
		return requestProcessorDimension;
	}

	public void setRequestProcessorDimension(
			RequestProcessorDimension requestProcessorDimension) {
		this.requestProcessorDimension = requestProcessorDimension;
	}

	public String getPrimaryContext() {
		return primaryContext;
	}

	public void setPrimaryContext(String primaryContext) {
		this.primaryContext = primaryContext;
	}

	public String getSecondaryContext() {
		return secondaryContext;
	}

	public void setSecondaryContext(String secondaryContext) {
		this.secondaryContext = secondaryContext;
	}

	public String getSubContexts() {
		return subContexts;
	}

	public void setSubContexts(String subContexts) {
		this.subContexts = subContexts;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public boolean isRequestCompleted() {
		return requestCompleted;
	}

	public void setRequestCompleted(boolean requestCompleted) {
		this.requestCompleted = requestCompleted;
	}

	public boolean isResponseSendToUser() {
		return responseSendToUser;
	}

	public void setResponseSendToUser(boolean responseSendToUser) {
		this.responseSendToUser = responseSendToUser;
	}

	public String getResponseItemsCode() {
		return responseItemsCode;
	}

	public void setResponseItemsCode(String responseItemsCode) {
		this.responseItemsCode = responseItemsCode;
	}

}
