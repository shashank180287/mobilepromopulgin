package com.mobile.tool.request.intr.jobs;

import static ch.lambdaj.Lambda.select;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.util.ToolQuartzJob;
import com.mobile.tool.inventory.entity.ItemMeasure;
import com.mobile.tool.request.intr.entity.UserRequestMeasure;
import com.mobile.tool.request.intr.processor.RequestProcessor;
@Component
@ToolQuartzJob(name = "RequestProcessingJob", cronExp = "0 0 1 * * ?")
public class RequestProcessingJob extends QuartzJobBean {

	@Autowired
	private DataHandler dataHandler;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		List<UserRequestMeasure> userRequestMeasures = dataHandler.fetchAllUserRequestMeasureForProcessing();
		MultiKeyMap itemsByServiceNameAndSubCatNameMap = new MultiKeyMap();
		Map<String, List<UserRequestMeasure>> userRequestByProcessorNameMap =  categorizeRequestsByProcessor(userRequestMeasures);
		for (String processorName : userRequestByProcessorNameMap.keySet()) {
			RequestProcessor processor;
			try {
				processor = (RequestProcessor) Class.forName(processorName).newInstance();
				for (UserRequestMeasure userRequestMeasure : userRequestByProcessorNameMap.get(processorName)) {
					List<ItemMeasure> items = getItemMeasursListByServiceNameAndSubCateName(itemsByServiceNameAndSubCatNameMap, userRequestMeasure.getServiceType().getName(), userRequestMeasure.getSubCategoryDimension().getName());
					List<ItemMeasure> matchedItemsCodeList = select(items, processor.getMatcherForSearchingItems(userRequestMeasure.getPrimaryContext(), userRequestMeasure.getSecondaryContext(), userRequestMeasure.getSubContexts()));
					if(!matchedItemsCodeList.isEmpty()){
						userRequestMeasure.setModifiedAt(new Date(Calendar.getInstance().getTimeInMillis()));
						userRequestMeasure.setResponseItemsCode(StringUtils.join(CollectionUtils.collect(matchedItemsCodeList, new Transformer() {
							@Override
							public Object transform(Object obj) {
								ItemMeasure item = (ItemMeasure) obj;
								return item.getItemCode();
							}
						}), ":"));
						userRequestMeasure.setRequestCompleted(true);
						dataHandler.insertOrUpdateUserRequestMeasure(userRequestMeasure);
					}
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.err.println("processor not found for "+processorName);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.err.println("processor not found for "+processorName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("processor not found for "+processorName);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List<ItemMeasure> getItemMeasursListByServiceNameAndSubCateName(MultiKeyMap itemsByServiceNameAndSubCatNameMap, String serviceName,
			String subCatName) {
		if(itemsByServiceNameAndSubCatNameMap.containsKey(serviceName, subCatName)){
			return (List<ItemMeasure>) itemsByServiceNameAndSubCatNameMap.get(serviceName, subCatName);
		}
		List<ItemMeasure> itemMeasures = dataHandler.fetchItemsByCategoryAndSubCategoryName(serviceName, subCatName);
		itemsByServiceNameAndSubCatNameMap.put(serviceName, subCatName, itemMeasures);
		return itemMeasures;
	}

	private Map<String, List<UserRequestMeasure>> categorizeRequestsByProcessor(List<UserRequestMeasure> userRequestMeasures) {
		Map<String, List<UserRequestMeasure>> reqByProcessorNameMap = new HashMap<String, List<UserRequestMeasure>>();
		for (UserRequestMeasure userRequestMeasure : userRequestMeasures) {
			String processor = userRequestMeasure.getRequestProcessorDimension().getProcessorClassName();
			List<UserRequestMeasure> userReqList;
			if(reqByProcessorNameMap.containsKey(processor)){
				userReqList = reqByProcessorNameMap.get(processor);
			}else{
				userReqList = new ArrayList<UserRequestMeasure>();
			}
			userReqList.add(userRequestMeasure);
			reqByProcessorNameMap.put(processor, userReqList);
		}
		return reqByProcessorNameMap;
	}
		
}
