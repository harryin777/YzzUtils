package com.yzz.springsecurity.security.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.acl.LastOwnerException;

/**
 * @ClassName AuthenticationFailure
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Component
@Slf4j
public class AuthenticationFailure implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		log.info("登录失败");
		response.getWriter().write(HttpStatus.SC_METHOD_FAILURE);
	}
}
