package com.yzz.multidatasource.controller;

import com.yzz.multidatasource.config.MyThreadOuter;
import com.yzz.multidatasource.service.AService;
import com.yzz.multidatasource.service.BService;
import com.yzz.multidatasource.service.CService;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName MultiController
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(tags = "多数据源测试")
@Data
public class MultiController {
	
	@Resource
	private AService aService;
	
	@Resource
	private BService bService;
	
	@Resource
	private CService cService;

	private ThreadFactory threadFactory = new CustomizableThreadFactory("myThread-");

	private ThreadPoolExecutor threadPoolExecutor =
			new ThreadPoolExecutor(4,
					10,
					1000,
					TimeUnit.MILLISECONDS,
					new ArrayBlockingQueue(8),
					threadFactory,
					new ThreadPoolExecutor.AbortPolicy());

	private String type;

//	@GetMapping("/testa")
//	public String testa(){
//		return aService.getOne().toString();
//	}

	@GetMapping("/testb")
	public String testb(){
		return bService.getOne().toString();
	}

	@GetMapping("/testc")
	public String testc(){
		return cService.getOne().toString();
	}

	@PostMapping("/multiThread")
	public String multiThread(@RequestBody List<List<String>> strList) throws ExecutionException, InterruptedException {
		List<List<String>> result = new ArrayList<>();
		for (int i = 0; i < strList.size(); i++) {
			FutureTask<List<String>> listFutureTask =
					(FutureTask<List<String>>) threadPoolExecutor.submit(new MyThreadOuter(threadPoolExecutor, strList.get(i), aService));
			result.add(listFutureTask.get());
		}
		return result.toString();
	}
	
	
	
}
