package com.yzz.springpractice.filter;

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
public class MyFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		System.out.println("这是过滤器 filter 开始");
		filterChain.doFilter(request, response);
		System.out.println("这是过滤器 filter 结束");
	}
}
