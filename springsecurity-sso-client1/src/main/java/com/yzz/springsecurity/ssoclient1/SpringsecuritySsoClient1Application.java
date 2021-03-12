package com.yzz.springsecurity.ssoclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class SpringsecuritySsoClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecuritySsoClient1Application.class, args);
	}
	
}
