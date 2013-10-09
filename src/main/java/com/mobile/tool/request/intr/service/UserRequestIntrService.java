package com.mobile.tool.request.intr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.entity.ServiceType;
import com.mobile.tool.core.entity.SubCategoryDimension;
import com.mobile.tool.inventory.mapper.ItemMeasureEntityToItemModelMapper;
import com.mobile.tool.inventory.model.Item;
import com.mobile.tool.request.intr.entity.RequestProcessorDimension;
import com.mobile.tool.request.intr.entity.UserRequestMeasure;
import com.mobile.tool.request.intr.processor.RequestProcessor;

@Service
public class UserRequestIntrService {

	@Autowired
	private DataHandler dataHandler;
	
	public boolean insertUserRequest(String userId, String serviceTypeCode, String subCategoryName,
			String requestTypeName, String context, String subContext) {
		ServiceType serviceType = dataHandler.fetchServiceTypeByCode(serviceTypeCode);
		if(serviceType==null)
			throw new IllegalArgumentException("Service Type Code not present.");
		
		SubCategoryDimension subCategoryDimension = dataHandler.fetchSubCategoryByServiceTypeAndSubCategoryName(serviceType, subCategoryName);
		if(subCategoryDimension==null)
			throw new IllegalArgumentException("Sub Category Name not present.");
		
		RequestProcessorDimension requestProcessorDimension = dataHandler.fetchRequestProcessorByRequestType(requestTypeName);
		if(requestProcessorDimension==null)
			throw new IllegalArgumentException("Request Type not present.");
		
		try{
			String secondaryContext=null;
			String subContexts=null;
			if(subContext.contains(":")){
				secondaryContext = subContext.split(":")[0];
				subContexts = subContext.substring(subContext.indexOf(":")+1);
			}else{
				secondaryContext = subContext;
			}
			
			UserRequestMeasure userRequestMeasure = new UserRequestMeasure(userId, serviceType, subCategoryDimension, requestProcessorDimension, context, secondaryContext, subContexts, new java.sql.Date(new Date().getTime()));
			dataHandler.insertOrUpdateUserRequestMeasure(userRequestMeasure);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public UserRequestMeasure getUserRequestMeasureForUser(String userId){
		return dataHandler.fetchProcessedUserRequestMeasureForUser(userId);
	}
	
	public List<Item> fetchItemsForUserRequestMeasure(UserRequestMeasure userProcessedRequestMeasure) {
		if(userProcessedRequestMeasure!=null){
			String[] itemCodes = userProcessedRequestMeasure.getResponseItemsCode().split(":");
			List<Item> items = new ArrayList<>();
			for (String itemCode : itemCodes) {
				items.add(ItemMeasureEntityToItemModelMapper.mapItemMeasureToItem(dataHandler.fetchItemsByitemCode(itemCode)));
			}
			return items;
		}
		return null;
	}

	public void insertOrUpdateUserRequest(UserRequestMeasure userRequestMeasure) {
		dataHandler.insertOrUpdateUserRequestMeasure(userRequestMeasure);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getAllequestProcessorTypeNames() {
		List requestProcessorDimensions = dataHandler.fetchAllRequestProcessor();
		CollectionUtils.transform(requestProcessorDimensions, new Transformer() {
			
			@Override
			public Object transform(Object arg0) {
				if(arg0 instanceof RequestProcessorDimension){
					return ((RequestProcessorDimension)arg0).getRequestType();
				}
				return null;
			}
		});
		return requestProcessorDimensions;
	}

	public JSONObject getSelectionsForRequestProcessor(String processorName) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		RequestProcessorDimension requestProcessorDimension = dataHandler.fetchRequestProcessorByRequestType(processorName);
		if(requestProcessorDimension!=null){
			String processorClassName = requestProcessorDimension.getProcessorClassName();
			RequestProcessor processor =  (RequestProcessor) Class.forName(processorClassName).newInstance();
			JSONObject[] selections = processor.getProcessorSelections();
			JSONObject selectionsObject = new JSONObject();
			int i=1;
			for (JSONObject selection : selections) {
				selectionsObject.put("selection"+i, selection);
				i++;
			}
			return selectionsObject;
		}
		throw new IllegalArgumentException("Processor not found for type:"+processorName);
	}

	
}
