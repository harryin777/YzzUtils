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

public class MyThreadInner implements Callable<String> {

	private AService aService;
	//注意这里用到了threadLocal，因为在下面的线程执行内容里，
	//才会根据不同的数据源设置不同的threadLocal
	private ThreadLocal threadLocal = new ThreadLocal();
	private String dataSource;

	public MyThreadInner(AService service, String dSource){
		this.aService = service;
		this.dataSource = dSource;
	}
	@Override
	public String call() throws Exception {
		threadLocal.set(dataSource);
		return aService.getOne(threadLocal).toString();
	}
}
