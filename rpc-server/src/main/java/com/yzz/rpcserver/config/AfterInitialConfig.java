package com.yzz.rpcserver.config;

import com.yzz.rpcserver.service.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @ClassName AfterInitialConfig
 * @Author yky
 * @Date 2021/3/20
 * @Version 1.0
 */
@Slf4j
@Component
public class AfterInitialConfig implements CommandLineRunner {

	@Resource
	private Server server;

	@Override
	public void run(String... args) throws Exception {
		log.info("springboot启动后执行的方法");
		server.start();
	}
}
