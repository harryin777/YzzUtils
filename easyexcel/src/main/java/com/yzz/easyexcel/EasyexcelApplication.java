package com.yzz.easyexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.yzz")
@SpringBootApplication
public class EasyexcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyexcelApplication.class, args);
	}

}
