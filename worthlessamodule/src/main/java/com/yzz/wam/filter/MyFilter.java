package com.yzz.wam.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyFilter
 * @Author yky
 * @Date 2021/3/3
 * @Version 1.0
 */
@Slf4j
@Component
public class MyFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		log.info("进入过滤器");
		filterChain.doFilter(request, response);
		log.info("完成过滤器");
	}
}
