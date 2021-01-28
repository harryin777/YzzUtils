package com.yzz.springpractice;

import com.yzz.springpractice.controller.StuController;
import com.yzz.springpractice.entity.Student;
import com.yzz.springpractice.entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
class AppTest {
	
	private ClassPathXmlApplicationContext context;
	
	@BeforeEach
	void init(){
		context = new ClassPathXmlApplicationContext("application.xml");
		System.out.println("容器启动完成");
	}
	
	@DisplayName("测试getBean")
	@Test
	void testGetBean() {
		Teacher teacher = context.getBean("t1", Teacher.class);
		System.out.println(teacher);
		Student student = context.getBean("student", Student.class);
		System.out.println(student);
		
	}
	
	@DisplayName("测试Aop")
	@Test
	void testAop() {
		//是否自动装配了controller里的student
		StuController stuController = context.getBean("StuController", StuController.class);
		System.out.println(stuController.getStudent().getName());
		
		//aop测试
		stuController.getStudentServiceImpl().getOne("!!!!");
		
		//为一个不实现任何接口的类创建代理测试
		stuController.getNoImplService().NoImpl();
	}
}