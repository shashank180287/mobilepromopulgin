package com.mobile.tool.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.tool.inventory.mapper.InventorySearchResponseMapper;
import com.mobile.tool.inventory.mapper.ItemRequestModelToItemModelMapper;
import com.mobile.tool.inventory.model.Item;
import com.mobile.tool.inventory.request.model.InventoryItemRequestModel;
import com.mobile.tool.inventory.request.model.InventorySearchRequest;
import com.mobile.tool.inventory.response.model.InventoryItemOperationResponse;
import com.mobile.tool.inventory.response.model.InventorySearchItem;
import com.mobile.tool.inventory.response.model.InventorySearchResponse;
import com.mobile.tool.inventory.service.InventorySystemService;
import com.mobile.tool.inventory.util.InventoryItemsFileUploader;

@Controller
@RequestMapping(value="/inventory")
public class InventorySystemController {

	private InventorySystemService inventorySystemService;
	
	@Autowired
	public InventorySystemController(InventorySystemService inventorySystemService){
		this.inventorySystemService = inventorySystemService;
	}
	
	@RequestMapping(value="/search", method= RequestMethod.GET)
	public @ResponseBody InventorySearchResponse searchItemsByCategoryAndSubCategory(InventorySearchRequest inventorySearchRequest){
		List<Item> itemsByCategoryAndSubCategory = inventorySystemService.fetchItemsByCategoryAndSubCategoryName(inventorySearchRequest.getCategoryName(), inventorySearchRequest.getSubCategoryName());
		return InventorySearchResponseMapper.mapToInventorySearchResponse(itemsByCategoryAndSubCategory, inventorySearchRequest.getCategoryName(), inventorySearchRequest.getSubCategoryName());
	}
	
	@RequestMapping(value="/uploadinventory", method= RequestMethod.GET)
	public String uploadFile(Model map){
		map.addAttribute("moduleHandler", InventoryItemsFileUploader.MODULE_KEY);
		return "fileuploader"; 
	}
	
	@RequestMapping(value="/{itemcode}", method= RequestMethod.GET)
	public @ResponseBody InventorySearchItem fetchItemDetailsByItemCode(@PathVariable(value="itemcode") String itemCode){
		Item item = inventorySystemService.fetchItemDetailsByItemCode(itemCode);
		return InventorySearchResponseMapper.mapItemModelToInventorySearchItem(item);
	}
	
	@RequestMapping(value="/{itemcode}", method= RequestMethod.POST)
	public @ResponseBody InventoryItemOperationResponse insertItemDetails(@RequestBody InventoryItemRequestModel inventoryItemRequest, @PathVariable(value="itemcode") String itemCode){
		inventoryItemRequest.setItemCode(itemCode);
		String status;
		String message;
		try{
			status = inventorySystemService.insertInventoryItemMeasure(ItemRequestModelToItemModelMapper.mapItemRequestModelToItem(inventoryItemRequest))==true?"SUCCESS":"FAILED";
			message = "Item inserted successfully";
		}catch (Exception e) {
			status ="FAILED";
			message = e.getMessage();
		}
		return new InventoryItemOperationResponse(status, message);
	}
	
	@RequestMapping(value="/{itemcode}", method= RequestMethod.PUT)
	public @ResponseBody InventoryItemOperationResponse updateItemDetails(@RequestBody InventoryItemRequestModel inventoryItemRequest, @PathVariable(value="itemcode") String itemCode){
		inventoryItemRequest.setItemCode(itemCode);
		String status;
		String message;
		try{
			status = inventorySystemService.updateInventoryItemMeasure(ItemRequestModelToItemModelMapper.mapItemRequestModelToItem(inventoryItemRequest))==true?"SUCCESS":"FAILED";
			message = "Item updated successfully";
		}catch (Exception e) {
			status ="FAILED";
			message = e.getMessage();
		}
		return new InventoryItemOperationResponse(status, message);
	}
	
	@RequestMapping(value="/{itemcode}", method= RequestMethod.DELETE)
	public @ResponseBody InventoryItemOperationResponse deleteItemDetails(@PathVariable(value="itemcode") String itemCode){
		String status;
		String message;
		try{
			status = inventorySystemService.deleteInventoryItemMeasure(itemCode)==true?"SUCCESS":"FAILED";
			message = "Item deleted successfully";
		}catch (Exception e) {
			status ="FAILED";
			message = e.getMessage();
		}
		return new InventoryItemOperationResponse(status, message);
	}
}
