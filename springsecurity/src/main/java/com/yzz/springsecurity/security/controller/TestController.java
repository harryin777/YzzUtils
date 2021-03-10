package com.yzz.springsecurity.security.controller;

import com.yzz.springsecurity.security.dao.UserDao;
import com.yzz.springsecurity.security.service.UserService;
import com.yzz.springsecurity.security.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TestController
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
@Slf4j
@RestController
public class TestController {
	
	@Resource
	private UserService userService;
	
	@GetMapping("/test1")
	public String test1(HttpServletRequest request){
		log.info("访问test1");
		String a1 = request.getParameter("a1");
		log.info("访问test1 结束");
		return "test1";
	}
	
	@GetMapping("/test2")
	public String test2(HttpServletRequest request){
		log.info("访问test2");
		String a1 = request.getParameter("a1");
		log.info("访问test2 结束");
		return "test2";
	}
}
