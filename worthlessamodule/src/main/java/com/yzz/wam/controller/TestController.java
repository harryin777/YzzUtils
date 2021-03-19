package com.yzz.wam.controller;

import com.yzz.hub.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResultVO testResultDTO(){
		return ResultVO.success().message("成功");
	}
}
