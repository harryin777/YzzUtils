package com.yzz.wam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ForwardAndRedirectController
 * @Author yky
 * @Date 2021/3/8
 * @Version 1.0
 */
@Slf4j
@Controller
public class ForwardAndRedirectController {


	@GetMapping("/tredirect")
	public String tredirect(){
		//直接返回string会在屏幕上打印出字符串，而不是跳转到相关页面，需要增加thymleaf或者mvc配置
		//以及把restController换成controller
		return "redirect:/index";
	}

	@RequestMapping("/testIndex")
	public String testIndex(){
		return "index";
	}
}
