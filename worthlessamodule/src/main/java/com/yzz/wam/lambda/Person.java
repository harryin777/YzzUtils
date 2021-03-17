package com.yzz.wam.lambda;

import lombok.*;

import javax.annotation.security.DenyAll;
import java.io.Serializable;

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
public class Person implements Serializable {

	@NonNull
	private String name;

	private int age;
}
