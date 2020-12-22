package com.yzz.practice_mybatisplus.controller;

import com.yzz.practice_mybatisplus.dao.StuDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Slf4j
@Controller
public class BpController {

	@Resource
	private StuDao stuDao;

	@RequestMapping(value = "/setStu", method = RequestMethod.POST)
	public String setStu(){
		return null;
	}
}
