package com.yzz.springpractice;

import com.yzz.springpractice.controller.StuController;
import com.yzz.springpractice.entity.Student;
import com.yzz.springpractice.entity.Teacher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName App
 * @Author yky
 * @Date 2021/1/20
 * @Version 1.0
 */
public class App {
	public static void main(String[] args) throws Exception{
		/**
		 * 2个测试点
		 * 1、spring对象的创建是在spring容器启动的时候就已经创建了 先打印techer构造器里的内容 再打印启动完成
		 * 2、spring容器里通一个对象id无论你取多少次都是同一个(单例)
		 */
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		System.out.println("容器启动完成");
		
		
	}
}
