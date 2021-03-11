package com.yzz.springsecurity.ssoserver.config.securityconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName MyAuthenticatedManager
 * @Author yky
 * @Date 2021/3/5
 * @Version 1.0
 */
@Slf4j
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	
	@Resource
	private PasswordEncoder passwordEncoder;

	@Resource
	private UserDetailService userDetailService;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String) authentication.getName();
		UserDetails userDetails = userDetailService.loadUserByUsername(username);

		if(userDetails == null){
			System.out.println("该用户不存在");
			return null;
		}

		String password = (String) authentication.getCredentials();
		if(!passwordEncoder.matches(password, userDetails.getPassword())){
			log.info("密码不符合");
			return null;
		}
		//注意这里的构造方法，多了一个参数：authority，可以在源码里看到如果只有两个参数
		//那么鉴权的结果就是false
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
				= new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
		return usernamePasswordAuthenticationToken;
	}
	
	// 这里是表明，我们现在写的authenticate方法支持哪一种token的验证
	// 因为上面返回的是一个UsernamePasswordAuthenticationToken，所以下面要用这个类的isAssignableFrom方法
	// 可以自定义一个Token，需要实现AbstractAuthenticationToken
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
