package com.yzz.springsecurity.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @ClassName MyAccessDecisionManager
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Component
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		//鉴权成功与否标志
		boolean flag = false;
		//如果是多个权限，匹配成功的个数
		int count = 0;
		
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		
		while (iterator.hasNext()){
			ConfigAttribute configAttribute = iterator.next();
			// 访问该请求url需要的角色信息
			String needRole = configAttribute.getAttribute();
			//如果只是登陆就能访问，即没有匹配到资源信息
			if ("ROLE_LOGIN".equals(needRole)){
				//判断是否登陆，没有登陆则authentication是AnonymousAuthenticationToken接口实现类的对象
				//因为如果登录的话，他会自动获得这个登录权限所以直接判断他登录没有
				if (authentication instanceof AnonymousAuthenticationToken){
					throw new BadCredentialsException("未登录");
				} else return;
			}
			//如果匹配上了资源信息，就拿登陆用户的权限信息来对比是否存在于已匹配的角色集合中
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(needRole)){
					count++;
				}
			}
		}
		//如果需要的权限个数和匹配成功的不等，权限不足
		if(count != configAttributes.size()){
			throw new AccessDeniedException("权限不足");
		}else{
			return;
		}
		
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
