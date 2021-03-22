package com.yzz.rpcserver.service.impl;

import com.yzz.rpcserver.service.ServiceForTest;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ServiceForTestImpl
 * @Author yky
 * @Date 2021/3/20
 * @Version 1.0
 */
@Slf4j
public class ServiceForTestImpl implements ServiceForTest {
	@Override
	public String thisIsTestMethod(String parameter) {
		return "这里是测试方法，参数："+ parameter;
	}
}
