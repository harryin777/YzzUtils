package com.yzz.springpractice.autowiredmyself;

/**
 * @ClassName TestController
 * @Author yky
 * @Date 2021/1/20
 * @Version 1.0
 */
public class TestController {
	
	@AutoWiredMyself
	TestObj testObj;
	
	public TestObj getTestObj() {
		return testObj;
	}
}
