package com.yzz.springsecurity.security.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AuthenticationAccessDeny
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Component
@Slf4j
public class AuthenticationAccessDeny implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("没有足够的权限");
		response.getWriter().write(HttpStatus.SC_FORBIDDEN);
	}
}
