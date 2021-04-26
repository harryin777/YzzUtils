package com.yzz.multidatasource.config;

import com.yzz.multidatasource.controller.MultiController;
import com.yzz.multidatasource.service.AService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * @ClassName MyThread
 * @Author yky
 * @Date 2021/4/26
 * @Version 1.0
 */
@Component
public class MyThread implements Callable<String> {

	private AService aService;

	public MyThread(AService service){
		this.aService = service;
	}
	@Override
	public String call() throws Exception {
		return aService.getOne().toString();
	}
}
