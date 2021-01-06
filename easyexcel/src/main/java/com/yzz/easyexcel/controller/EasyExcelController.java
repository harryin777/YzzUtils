package com.yzz.easyexcel.controller;

import com.yzz.easyexcel.service.EasyExcelService;
import com.yzz.easyexcel.vo.Stu;
import com.yzz.hub.dto.ResultDTO;
import com.yzz.hub.vo.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EasyExcelController
 * @Author yky
 * @Date 2021/1/4
 * @Version 1.0
 */
@Api(tags = "EasyExcel服务")
@Slf4j
@RestController
@RequestMapping("/EasyExcelService")
public class EasyExcelController {
	
	@Resource
	private EasyExcelService easyExcelService;
	
	@ApiOperation("写入excel")
	@PostMapping("/writeExcel")
	public ResultDTO writeExcel(){
		try{
			easyExcelService.writeData();
		}catch (Exception e){
			return new ResultDTO(false, StatusCode.error, "失败", null);
		}
		return new ResultDTO(true, StatusCode.success, "成功", null);
	}
	
	@ApiOperation("读取excel")
	@GetMapping("/readExcel")
	public ResultDTO readExcel(){
		List<Stu> list = new ArrayList<>();
		try{
			list = easyExcelService.readData();
		}catch (Exception e){
			return new ResultDTO(false, StatusCode.error, "失败", null);
		}
		return new ResultDTO(true, StatusCode.success, "成功", list);
	}


}
