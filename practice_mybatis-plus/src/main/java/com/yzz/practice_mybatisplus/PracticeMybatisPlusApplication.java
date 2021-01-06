package com.yzz.practice_mybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan("com.yzz")
@SpringBootApplication
public class PracticeMybatisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeMybatisPlusApplication.class, args);
	}

}
