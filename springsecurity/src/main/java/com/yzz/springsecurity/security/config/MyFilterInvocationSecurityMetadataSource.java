package com.yzz.springsecurity.security.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.springsecurity.security.dao.MenuDao;
import com.yzz.springsecurity.security.entity.Menu;
import com.yzz.springsecurity.security.entity.Role;
import com.yzz.springsecurity.security.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName MyFilterInvocationSecurityMetadataSource
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Component
@Slf4j
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	@Resource
	private MenuDao menuDao;
	
	@Resource
	private MenuService menuService;
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		
		List<Menu> allMenus = menuDao.selectList(new QueryWrapper<Menu>());
		for (Menu menu : allMenus) {
			if (antPathMatcher.match(menu.getUrlPattern(), requestUrl)) {
				List<Role> roles = menuService.getRoleByMenuId(menu.getId());
				String[] roleArr = new String[roles.size()];
				for (int i = 0; i < roleArr.length; i++) {
					roleArr[i] = roles.get(i).getRoleName();
				}
				return SecurityConfig.createList(roleArr);
			}
		}
		return SecurityConfig.createList("ROLE_LOGIN");
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}
