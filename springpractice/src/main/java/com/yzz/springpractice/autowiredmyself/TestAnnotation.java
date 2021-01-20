package com.yzz.springpractice.autowiredmyself;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @ClassName TestAnnotation
 * @Author yky
 * @Date 2021/1/20
 * @Version 1.0
 *
 * 模拟autowired的过程
 */
public class TestAnnotation {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class<?> clazz1 = Class.forName("com.yzz.springpractice.autowiredmyself.TestController");
		Class<?> clazz2 = TestController.class;
		
		//通过反射获取注解所在类
		TestController testController = new TestController();
		Class clazz = testController.getClass();
		//遍历属性看哪个有autowired注解
		Stream.of(clazz.getDeclaredFields()).forEach(e -> {
			AutoWiredMyself annotation = e.getAnnotation(AutoWiredMyself.class);
			if(annotation != null){
				e.setAccessible(true);
				//获取被autowired修饰的类的类型
				Class<?> type = e.getType();
				Object o = null;
				try {
					//实例化一个类
					o = type.newInstance();
					//给该属性赋值
					e.set(testController, o);
				} catch (InstantiationException ex) {
					ex.printStackTrace();
				} catch (IllegalAccessException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		System.out.println(testController.getTestObj().toString());
	}
}
