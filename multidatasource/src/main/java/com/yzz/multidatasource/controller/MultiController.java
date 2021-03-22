package com.yzz.multidatasource.controller;

import com.yzz.multidatasource.annotation.UsingDataSource;
import com.yzz.multidatasource.service.AService;
import com.yzz.multidatasource.service.BService;
import com.yzz.multidatasource.service.CService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MultiController
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
@Slf4j
@RestController
public class MultiController {
	
	@Resource
	private AService aService;
	
	@Resource
	private BService bService;
	
	@Resource
	private CService cService;



	@GetMapping("/testa")
	public String testa(){
		return aService.getOne().toString();
	}

	@GetMapping("/testb")
	public String testb(){
		return bService.getOne().toString();
	}

	@GetMapping("/testc")
	public String testc(){
		return cService.getOne().toString();
	}
	
	
	
}
