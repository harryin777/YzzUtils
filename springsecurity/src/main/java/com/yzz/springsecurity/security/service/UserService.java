package com.yzz.springsecurity.security.service;

import com.yzz.springsecurity.security.vo.UserVO;

/**
 * @ClassName UserService
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
public interface UserService {
	
	
	UserVO getOne(String userName);
}
