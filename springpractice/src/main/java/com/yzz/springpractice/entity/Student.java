package com.yzz.springpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Student
 * @Author yky
 * @Date 2021/1/20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
	private String name;
	
	private String sex;
	
	private int age;
	
	private Set<String> hobbis;
	
	private List<String> courses;
	
	private Map<String, Double> scoreMap;
	
	private Teacher[] teachers;
	
	
}
