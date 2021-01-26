package com.yzz.wam.lambda;

import org.apache.tomcat.util.buf.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.*;

//加上这个是启动了springboot环境，有时候单纯测试简单的写法不需要，就注释掉
//@SpringBootTest()
class PersonTest {
	
	private List<Person> personList = new ArrayList<>();
	private List<String> stringList = new ArrayList<>();
	
	@BeforeEach
	void init(){
		
		personList.add(new Person("a",1));
		personList.add(new Person("b",2));
		personList.add(new Person("oipb",3));
		personList.add(new Person("abc",4));
		personList.add(new Person("bad",5));
		
		stringList.add("abc");
		stringList.add("ajjsjj");
		stringList.add("osbm");
		stringList.add("osbm666");
	}
	
	@DisplayName("测试Consumer用法")
	@Test
	void testConsumer(){
		Consumer<Person> giveRais = e -> e.setName(e.getName()+"!!!");
		personList.forEach(giveRais);
		System.out.println(personList);
	}
	
	@DisplayName("测试filter用法")
	@Test
	void testfilter(){
		personList.stream()
				.limit(1)
				.filter(e -> e.getName().contains("a")&&e.getName().contains("b"))
				.forEach(System.out::println);
	}
	
	@DisplayName("测试Sort用法")
	@Test
	void testSort(){
		personList.stream()
				.sorted((e1, e2) -> e2.getName().length() - e1.getName().length())
				.forEach(System.out::println);
		
		Person personMin = personList.stream()
				.min((e1, e2) -> e1.getName().length() - e2.getName().length())
				.get();
		System.out.println("名字最短："+personMin.getName());
		Person personMax = personList.stream()
				.max((e1, e2) -> e1.getName().length() - e2.getName().length())
				.get();
		System.out.println("名字最长："+personMax.getName());
	}
	
	@DisplayName("测试Collect用法")
	@Test
	void testCollect(){
		String nameCollect = personList.stream()
				.map(Person::getName)
				.collect(joining(" ; "));
		//注意这个只能是字符，上面那个可以是任意
		String strAppend = StringUtils.join(stringList, ';');
		System.out.println("自定义类型 拼接名字"+nameCollect);
		System.out.println("基本类型 拼接"+strAppend);
		
	}
	
}