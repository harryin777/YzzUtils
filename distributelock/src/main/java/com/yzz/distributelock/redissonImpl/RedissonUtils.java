package com.yzz.distributelock.redissonImpl;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedssionLock
 * @Author yky
 * @Date 2021/3/29
 * @Version 1.0
 */
@Slf4j
@Component
public class RedissonUtils {

	private static RedissonClient redissonClient = RedissonConfig.getRedisson();


	public static void lock(String lockName) {
		String key = lockName;
		RLock myLock = redissonClient.getLock(key);
		//lock提供带timeout参数，timeout结束强制解锁，防止死锁
		myLock.lock(2, TimeUnit.SECONDS);
		 /*
		 1. 最常见的使用方法
		lock.lock();
		 2. 支持过期解锁功能,10秒以后自动解锁, 无需调用unlock方法手动解锁
		lock.lock(10, TimeUnit.SECONDS);
		 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
        try {
            boolean res = mylock.tryLock(3, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
		log.info("======lock======" + Thread.currentThread().getName());
	}

	public static void unLock(String lockName) {
		String key = lockName;
		RLock myLock = redissonClient.getLock(key);
		myLock.unlock();
		log.info("======unlock======" + Thread.currentThread().getName());
	}
}
