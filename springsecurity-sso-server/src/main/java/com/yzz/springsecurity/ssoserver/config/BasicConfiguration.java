package com.yzz.springsecurity.ssoserver.config;

import com.yzz.hub.utils.SpringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @ClassName Configuration
 * @Author yky
 * @Date 2021/3/11
 * @Version 1.0
 */
@Configuration
@MapperScan("com.yzz.springsecurity.ssoserver.dao")
public class BasicConfiguration {
	
	@Resource
	private SpringUtils springUtils;
}
