package com.yzz.springsecurity.ssoclient1.config.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @ClassName SecurityConfig
 * @Author yky
 * @Date 2021/3/11
 * @Version 1.0
 */
@Configuration
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.logout().logoutSuccessUrl("http://localhost:8087/logout")
				.and()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.csrf().disable();
		
	}
}
