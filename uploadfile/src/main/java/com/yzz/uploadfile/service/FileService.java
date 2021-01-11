package com.yzz.uploadfile.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	boolean upLoadFile(MultipartFile file);
}
