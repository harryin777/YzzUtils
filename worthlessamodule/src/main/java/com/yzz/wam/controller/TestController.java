package com.yzz.wam.controller;

import com.yzz.hub.dto.ResultDTO;
import com.yzz.wam.lambda.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Author yky
 * @Date 2021/3/3
 * @Version 1.0
 */
@RestController
@RequestMapping("/testController")
public class TestController {
	
	@GetMapping("/testOne")
	public String testOne(){
		return "testOne";
	}
	
	@GetMapping("/testDTO")
	public ResultDTO testResultDTO(){
		return ResultDTO.success().message("成功");
	}
}
