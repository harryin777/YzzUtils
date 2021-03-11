package com.yzz.springsecurity.ssoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.yzz")
@SpringBootApplication
public class SpringsecuritySsoServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringsecuritySsoServerApplication.class, args);
	}
	
}
