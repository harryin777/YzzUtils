package com.yzz.uploadfile.controller;

import com.yzz.hub.dto.ResultDTO;
import com.yzz.uploadfile.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @ClassName FileController
 * @Author yky
 * @Date 2021/1/10
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "文件管理模块")
@RequestMapping("/fileMana")
public class FileController {

	@Resource
	private FileService fileService;

	@ApiOperation("上传文件")
	@PostMapping("/upLoadFile")
	public ResultDTO upLoadFile(@RequestParam MultipartFile file, HttpServletRequest request){
		log.info("访问接口:上传文件");
		String path = request.getSession().getServletContext().getRealPath(request.getRequestURI());
		log.info(path);
		boolean back = fileService.upLoadFile(file);
		log.info("访问接口:上传文件，结束");
		return ResultDTO.success().data(null);
	}

}
