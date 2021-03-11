package com.yzz.springsecurity.ssoserver.service;


import com.yzz.springsecurity.ssoserver.entity.Role;

import java.util.List;

/**
 * @ClassName UserService
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
public interface UserService {
	
	List<Role> getRolesByUserId(int userId);

}
