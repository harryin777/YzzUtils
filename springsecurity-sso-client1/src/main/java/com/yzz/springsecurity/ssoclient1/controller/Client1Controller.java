package com.yzz.springsecurity.ssoclient1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName Client1Controller
 * @Author yky
 * @Date 2021/3/11
 * @Version 1.0
 */
@Controller
public class Client1Controller {
	
	@GetMapping("/testC1")
	@ResponseBody
	public String testC1(){
		return "testC1";
	}
}
