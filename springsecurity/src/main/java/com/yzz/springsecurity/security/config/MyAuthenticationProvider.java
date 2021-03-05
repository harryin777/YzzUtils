package com.yzz.springsecurity.security.config;

import com.yzz.springsecurity.security.service.UserService;
import com.yzz.springsecurity.security.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @ClassName MyAuthenticatedManager
 * @Author yky
 * @Date 2021/3/5
 * @Version 1.0
 */
@Slf4j
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Resource
	private UserService userService;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String) authentication.getName();
		String password = (String) authentication.getCredentials();
		UserVO userVO = userService.getOne(username);
		if(userVO == null){
			System.out.println("该用户不存在");
			return null;
		}
		
		
		if(passwordEncoder.matches(userVO.getPassword(), password)){
			log.info("密码复合");
		}
		//注意这里的构造方法，多了一个参数：authority，可以在源码里看到如果只有两个参数
		//那么鉴权的结果就是false
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
				= new UsernamePasswordAuthenticationToken(username, password, userVO.getAuthorities());
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
