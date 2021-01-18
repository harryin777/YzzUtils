package com.yzz.springsecurity.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName SpringSecApplication
 * @Author yky
 * @Date 2021/1/18
 * @Version 1.0
 */
@ComponentScan("com.yzz")
@SpringBootApplication
public class SpringSecApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecApplication.class, args);
	}
}
