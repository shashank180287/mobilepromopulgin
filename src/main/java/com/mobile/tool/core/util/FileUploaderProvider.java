package com.mobile.tool.core.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mobile.tool.inventory.util.InventoryItemsFileUploader;

@Component
public class FileUploaderProvider {
	private static Map<String, FileUploader> fileUploaderByModuleNameMap;
	
	@Autowired
	private FileUploaderProvider(InventoryItemsFileUploader inventoryItemsFileUploader){
		fileUploaderByModuleNameMap = new HashMap<String, FileUploader>();
		fileUploaderByModuleNameMap.put(InventoryItemsFileUploader.MODULE_KEY, inventoryItemsFileUploader);
	}
	
	public static FileUploader getFileUploader(String moduleName){
		return fileUploaderByModuleNameMap.get(moduleName);
	}
}
