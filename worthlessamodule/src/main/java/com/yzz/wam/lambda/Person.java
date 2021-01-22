package com.yzz.wam.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.security.DenyAll;

/**
 * @ClassName Person
 * @Author yky
 * @Date 2021/1/21
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	private String name;
}
