package com.yzz.wam.config;

import com.yzz.wam.intercepter.MyInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyConfig
 * @Author yky
 * @Date 2021/3/3
 * @Version 1.0
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptors());
	}
	
	
}
