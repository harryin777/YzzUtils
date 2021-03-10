package com.yzz.hub.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringUtils
 * @Author yky
 * @Date 2021/3/10
 * @Version 1.0
 *
 * Spring上下文工具类。注意：使用之前，请确保ApplicationContext已成功完成初始化。
 */
@Slf4j
@Component
public class SpringUtils implements ApplicationContextAware {
	
	
	/**
	 * 初始化上下文
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtils.context = applicationContext;
	}
	
	private static ApplicationContext context;
	
	
	
	/**
	 * 根据类型获取Bean
	 *
	 * @param cls Bean类
	 * @param <T> Bean类型
	 * @return Bean对象
	 */
	public static <T> T getBean(Class<T> cls) {
		return context == null ? null : context.getBean(cls);
	}
	
	/**
	 * 根据名称获取Bean
	 *
	 * @param name Bean名称
	 * @return Bean对象
	 */
	public static Object getBean(String name) {
		return context == null ? null : context.getBean(name);
	}
	
	/**
	 * 根据Bean名称和类获取Bean对象
	 *
	 * @param name Bean名称
	 * @param cls Bean类
	 * @param <T> Bean类型
	 * @return Bean对象
	 */
	public static <T> T getBean(String name, Class<T> cls) {
		return context == null ? null : context.getBean(name, cls);
	}
	
	
}
