package com.mobile.tool.core.web.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

	private List<MultipartFile> files;

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}
