package com.yzz.wam.lambda;

import lombok.*;

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
@RequiredArgsConstructor
public class Person {

	@NonNull
	private String name;

	private int age;
}
