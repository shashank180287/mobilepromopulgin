package com.mobile.tool.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.tool.core.util.FileUploader;
import com.mobile.tool.core.util.FileUploaderProvider;
import com.mobile.tool.core.web.model.FileUploadForm;

@Controller
public class FileUploaderController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm, @RequestParam String uploadFileType, @RequestParam String moduleHandler,
    		Model map) {
        List<MultipartFile> files = uploadForm.getFiles();
        FileUploader fileUploader = FileUploaderProvider.getFileUploader(moduleHandler);
        Map<String, Boolean> fileNamesWithStatus = new HashMap<String, Boolean>();
        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                String fileName = multipartFile.getOriginalFilename();
                fileNamesWithStatus.put(fileName, fileUploader.uploadMultiPartFile(multipartFile, uploadFileType));
            }
        }
        map.addAttribute("files", fileNamesWithStatus);
        return "file_upload_result";
    }
}
