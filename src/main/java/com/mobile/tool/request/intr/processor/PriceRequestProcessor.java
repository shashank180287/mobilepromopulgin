package com.mobile.tool.request.intr.processor;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import java.util.Arrays;

import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.tool.inventory.entity.ItemMeasure;

public class PriceRequestProcessor implements RequestProcessor{

	private static final String OPTION_LESS_THAN="Less Than";
	private static final String OPTION_MORE_THAN="More Than";
	private static final String OPTION_EQUAL_TO="Equal To";
	
	@Override
	public Matcher<?> getMatcherForSearchingItems(String primaryContext, String secondaryContext, String subContexts) {
		switch (primaryContext) {
		case OPTION_LESS_THAN:
			return having(on(ItemMeasure.class).getEffectivePrice(), lessThan(Float.parseFloat(secondaryContext)));
		case OPTION_MORE_THAN:
			return having(on(ItemMeasure.class).getEffectivePrice(), greaterThan(Float.parseFloat(secondaryContext)));
		default :
			return having(on(ItemMeasure.class).getEffectivePrice(), equalTo(Float.parseFloat(secondaryContext)));
		}
	}

	@Override
	public JSONObject[] getProcessorSelections() {
		JSONObject[] selections = new JSONObject[2];
		try{
			selections[0] = new JSONObject().put("type", "list").put("values", new JSONArray(Arrays.asList(OPTION_MORE_THAN, OPTION_LESS_THAN, OPTION_EQUAL_TO)));
			selections[1] = new JSONObject().put("type", "input");
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return selections;
	}

}
