package com.yzz.springsecurity.ssoserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController
 * @Author yky
 * @Date 2021/3/11
 * @Version 1.0
 */
@Slf4j
@Controller
public class LoginController {
	
	@RequestMapping("/loginIndex")
	public String loginIndex(){
		log.info("访问loginIndex");
		return "index";
	}
}
