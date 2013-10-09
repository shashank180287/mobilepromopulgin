package com.mobile.tool.request.intr.processor;

import org.hamcrest.Matcher;
import org.json.JSONObject;


public interface RequestProcessor {

	public Matcher<?> getMatcherForSearchingItems(String primaryContext, String secondaryContext, String subContexts);

	public JSONObject[] getProcessorSelections();
	
}
