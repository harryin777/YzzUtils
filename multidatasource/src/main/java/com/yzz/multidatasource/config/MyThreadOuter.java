package com.yzz.multidatasource.config;

import com.yzz.multidatasource.service.AService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName MyThread1
 * @Author yky
 * @Date 2021/4/27
 * @Version 1.0
 */
public class MyThreadOuter implements Callable<List<String>> {
	
	private ThreadPoolExecutor threadPoolExecutor;
	private List<String> dataSourceList;
	private AService aService;
	
	public MyThreadOuter(ThreadPoolExecutor threadPoolExecutor,
	                     List<String> dataSourceList,
	                     AService aService){
		this.threadPoolExecutor = threadPoolExecutor;
		this.dataSourceList = dataSourceList;
		this.aService = aService;
	}
	
	@Override
	public List<String> call() throws Exception {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < dataSourceList.size(); i++) {
			FutureTask<String> futureTask =
					(FutureTask<String>) threadPoolExecutor.submit(new MyThreadInner(aService, dataSourceList.get(i)));
			result.add(futureTask.get());
		}
		return result;
	}
}
