package com.yzz.distributelock.redisImpl;

import com.yzz.hub.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName Service
 * @Author yky
 * @Date 2021/3/29
 * @Version 1.0
 */
@Slf4j
@Component
public class Service {

	public void doSomeThing(){
		String localProcessors = String.valueOf(Runtime.getRuntime().availableProcessors());
		boolean isGetKey = RedisUtils.getLock(localProcessors, 2000);
		if(!isGetKey){
			log.info("没有获取到锁！！！");
			return;
		}

		log.info("获取到了锁");
		try{
			log.info("现在开始进行业务处理");
		}finally {
			RedisUtils.delete(localProcessors);
		}
	}
}
