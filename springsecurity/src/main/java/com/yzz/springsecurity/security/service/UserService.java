package com.yzz.springsecurity.security.service;

import com.yzz.springsecurity.security.entity.Role;
import com.yzz.springsecurity.security.vo.UserVO;

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
