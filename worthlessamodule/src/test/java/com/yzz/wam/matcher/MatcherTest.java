package com.yzz.wam.matcher;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName MatcherTest
 * @Author yky
 * @Date 2021/3/24
 * @Version 1.0
 */
public class MatcherTest {
	
	@Test
	void testMather(){
		Pattern pattern = Pattern.compile("Java");
		String test = "123Java456";
		
		Matcher matcher = pattern.matcher(test);
		matcher.find();
		System.out.println(matcher.start());//返回3
		System.out.println(matcher.end());//返回7
		System.out.println(matcher.group());//返回Java
		
	}
}
