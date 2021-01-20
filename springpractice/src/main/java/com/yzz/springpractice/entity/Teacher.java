package com.yzz.springpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Teacher
 * @Author yky
 * @Date 2021/1/20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {
	private String name;
	
	private String sex;
	
	private int age;
	
	private String phone;
}
