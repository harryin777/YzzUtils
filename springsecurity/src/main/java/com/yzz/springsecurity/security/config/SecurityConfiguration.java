package com.yzz.springsecurity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName SecurityConfiguration
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.httpBasic() httpBasic就是弹出一个对话框的那种认证
		http.formLogin()//开启表单认证，即使不配置这个，2.3.9默认是表单
				.loginPage("/loginM.html")//这里是配置默认的登录页面
				.loginProcessingUrl("/login123/123")//这里是指登录页面action的url，然后springsecurity就会自动处理登录
				.and()
				.authorizeRequests()
				.antMatchers("/loginM.html", "/login123/123")
				.permitAll()//开启授权配置
				.anyRequest()//所有请求
				.authenticated()//都需要认证
				;


	}
	
	/**
	 * 加密组件
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
