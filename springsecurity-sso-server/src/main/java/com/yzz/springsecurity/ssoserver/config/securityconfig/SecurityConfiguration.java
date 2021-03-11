package com.yzz.springsecurity.ssoserver.config.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

/**
 * @ClassName SecurityConfiguration
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Resource
	private AuthenticationSuccess authenticationSuccess;
	
	@Resource
	private AuthenticationFailure authenticationFailure;
	
	@Resource
	private MyAuthenticationEntryPoint myauthenticationEntryPoint;
	
	@Resource
	private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
	
	@Resource
	private AuthenticationLogout authenticationLogout;
	
	@Resource
	private AuthenticationAccessDeny authenticationAccessDeny;
	
	@Resource
	private MyAccessDecisionManager myAccessDecisionManager;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.httpBasic() httpBasic就是弹出一个对话框的那种认证
		http.authorizeRequests()
					.antMatchers("/login.html", "/login123/123")
					.permitAll()//开启授权配置
					.anyRequest()//所有请求
//					.access("")
					.authenticated()//都需要认证
					.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
						@Override
						public <O extends FilterSecurityInterceptor> O postProcess(O object) {
							object.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
							object.setAccessDecisionManager(myAccessDecisionManager);
							return object;
						}
					})
				.and()
					.formLogin()//开启表单认证，即使不配置这个，2.3.9默认是表单
					.loginPage("/login.html")//这里是配置默认的登录页面
					.loginProcessingUrl("/login123/123")//这里是指登录页面action的url，然后springsecurity就会自动处理登录
					.successHandler(authenticationSuccess)
					.failureHandler(authenticationFailure)
				.and()
					.logout()
					.logoutUrl("/logout")
					.logoutSuccessHandler(authenticationLogout)
					.and()
					.exceptionHandling()
					.accessDeniedHandler(authenticationAccessDeny)
	//				.authenticationEntryPoint(myauthenticationEntryPoint)
					
					;


	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// web.ignoring是直接绕开spring security的所有filter，直接跳过验证
		web.ignoring().antMatchers(
				"/sso/**",
				"/sync/**",
				"/findUserSysAuth2",
				"/label/**",
				"/Bi/findBiUserById",
				"/encodeByUserId",
				//注意这里如果没有登录页，会发生一直重定向的问题
				"/login.html",
				"/**.html",
				"/css/**",
				"images/**",
				"/js/**"
		);
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
