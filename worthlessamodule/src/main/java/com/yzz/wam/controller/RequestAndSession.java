package com.yzz.wam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName RequestAndSession
 * @Author yky
 * @Date 2021/3/8
 * @Version 1.0
 *
 * request.getParameter可以获取到session中的参数吗，不可以呀
 */
@RestController
@Slf4j
public class RequestAndSession {

	@GetMapping("/saveP")
	public String saveP(HttpSession session){
		session.setAttribute("test", "123");
		return "存储成功";
	}

	@GetMapping("/getP")
	public String getP(HttpSession session, HttpServletRequest request){
		log.info("request中的参数,{}", request.getParameter("test"));
		return (String) session.getAttribute("test");
	}
}
