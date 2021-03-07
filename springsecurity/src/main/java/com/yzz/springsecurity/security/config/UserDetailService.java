package com.yzz.springsecurity.security.config;

import com.yzz.springsecurity.security.vo.UserVO;
import com.yzz.springsecurity.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName UserDetailService
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
@Slf4j
@Component
public class UserDetailService implements UserDetailsService {

	@Resource
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//数据库获取到用户
		UserVO userVO = userService.getOne(username);
		//注意这个地方的返回值，如果属性中的password不是用BCryptPasswordEncoder加密过的话，会报错
		return userVO;
	}
}
