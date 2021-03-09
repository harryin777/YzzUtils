package com.yzz.springsecurity.security.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AuthenticationEntryPoint
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 *
 * 这个类实现了AuthenticationEntryPoint接口以后会替换掉原来的LoginUrlAuthenticationEntryPoint
 * 后者的作用是当访问localhost:9099这个url的时候回重定向到配置的loginPage
 * 但是自定义实现了之后，就按照自定义的规则来
 */
@Component
@Slf4j
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		log.error("出现异常, {}", authException.getMessage());
		response.getWriter().write(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		
	}
}
