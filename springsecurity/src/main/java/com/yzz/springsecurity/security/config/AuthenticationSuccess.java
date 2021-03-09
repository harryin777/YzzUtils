package com.yzz.springsecurity.security.config;

import com.yzz.hub.utils.AccessAddressUtil;
import com.yzz.hub.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AuthenticationSuccess
 * @Author yky
 * @Date 2021/3/8
 * @Version 1.0
 */
@Component
@Slf4j
public class AuthenticationSuccess implements AuthenticationSuccessHandler {
	
	@Value("${token.expirationSeconds}")
	private int expirationSeconds;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	
		//获取当前用户名
		String username = authentication.getName();
		//获取请求ip
		String ip = AccessAddressUtil.getIpAddress(request);
		Map<String, Object> map = new HashMap<>();
		map.put("ip", ip);
		//生成token
		String token_new = JwtUtils.generateToken(username, expirationSeconds, map);
		log.info("生成的token, {}", token_new);
		//token返回给前端
		response.getWriter().write(token_new);
	}
}
