package com.yzz.uploadfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan("com.yzz")
@SpringBootApplication
public class UploadfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadfileApplication.class, args);
	}

}
