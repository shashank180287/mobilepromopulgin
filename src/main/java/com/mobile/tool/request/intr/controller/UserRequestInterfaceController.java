package com.mobile.tool.request.intr.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.tool.request.intr.request.RequestProcessorRequest;
import com.mobile.tool.request.intr.request.UserRequestPostingRequest;
import com.mobile.tool.request.intr.response.RequestProcessorDetails;
import com.mobile.tool.request.intr.response.RequestProcessorOperationResponse;
import com.mobile.tool.request.intr.response.UserRequestPostingResponse;
import com.mobile.tool.request.intr.service.RequestProcessorSevice;
import com.mobile.tool.request.intr.service.UserRequestIntrService;

@Controller
@RequestMapping(value="/requestintr")
public class UserRequestInterfaceController {

	@Autowired
	private UserRequestIntrService userRequestInterfaceService;
	@Autowired
	private RequestProcessorSevice requestProcessorSevice;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public @ResponseBody UserRequestPostingResponse postUserRequest(@RequestBody UserRequestPostingRequest userRequestPostingRequest){
		boolean isRequestPostedSuccessfully = false;
		String responseMessage;
		try{
			isRequestPostedSuccessfully= userRequestInterfaceService.insertUserRequest(userRequestPostingRequest.getId(), userRequestPostingRequest.getCode(),
																				userRequestPostingRequest.getSubCat(), userRequestPostingRequest.getReqTypeName(), userRequestPostingRequest.getCtx(), userRequestPostingRequest.getSubCtx());
			responseMessage = "Request Posted Successfully";
		}catch (Exception e) {
			isRequestPostedSuccessfully = false;
			responseMessage = e.getMessage();
		}
		return new UserRequestPostingResponse(Boolean.toString(isRequestPostedSuccessfully), responseMessage);
	}
	
	@RequestMapping(value="/types", method=RequestMethod.GET)
	public @ResponseBody String getAllRequestProcessorTypes(){
		List<String> requestProcessorTypeNamesList = userRequestInterfaceService.getAllequestProcessorTypeNames();
		return requestProcessorTypeNamesList.toString();
	}
	
	@RequestMapping(value="/processordetails/{processorName}", method=RequestMethod.GET)
	public @ResponseBody RequestProcessorDetails getRequestProcessorDetailsByName(@PathVariable String processorName) throws JSONException{
		RequestProcessorDetails requestProcessorDetails = new RequestProcessorDetails(processorName);
		try{
			requestProcessorDetails.setSelection(userRequestInterfaceService.getSelectionsForRequestProcessor(processorName).toString());
		}catch (Exception e) {
			requestProcessorDetails.setSelection(new JSONObject().put("error", e.getMessage()).toString());
		}
		return requestProcessorDetails;
	}
	
	@RequestMapping(value="/processor", method=RequestMethod.POST)
	public @ResponseBody RequestProcessorOperationResponse insertRequestProcessor(@RequestBody RequestProcessorRequest requestProcessorRequest){
		try{
			requestProcessorSevice.insertRequestProcessor(requestProcessorRequest.getRequestType(), requestProcessorRequest.getProcessorClass(), requestProcessorRequest.getNoOfContexts(), requestProcessorRequest.getNoOfSubContexts());
			return new RequestProcessorOperationResponse("SUCCESS", "New Request Processor inserted Successfully");
		}catch (Exception e) {
			e.printStackTrace();
			return new RequestProcessorOperationResponse("FAILED", "New Request Processor inserted failed."+e.getMessage());
		}
	}
}
