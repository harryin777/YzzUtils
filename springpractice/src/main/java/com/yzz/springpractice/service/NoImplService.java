package com.yzz.springpractice.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName NoImplService
 * @Author yky
 * @Date 2021/1/28
 * @Version 1.0
 */
@Component
public class NoImplService {
	
	public void NoImpl(){
		System.out.println("当前类没有实现任何接口");
	}
}
