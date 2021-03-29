package com.yzz.distributelock.controller;

import com.yzz.distributelock.redissonImpl.RedissonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestController
 * @Author yky
 * @Date 2021/3/29
 * @Version 1.0
 */
@Slf4j
@RestController
public class TestController {

	private static int count = 0;

	@GetMapping("/test1")
	public int test1(){
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						count++;
					}
				}
			});
		}
		return count;
	}

	/**
	 * redisson不主动释放锁
	 * @return
	 */
	@GetMapping("/test2")
	public int test2(){
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					RedissonUtils.lock("key");
					for (int j = 0; j < 100; j++) {
						count++;
					}
				}
			});
		}
		return count;
	}
}
