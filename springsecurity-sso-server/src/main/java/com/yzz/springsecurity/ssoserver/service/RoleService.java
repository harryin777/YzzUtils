package com.yzz.springsecurity.ssoserver.service;



import com.yzz.springsecurity.ssoserver.entity.Role;

import java.util.List;

/**
 * @ClassName RoleService
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
public interface RoleService {
	
	List<Role> getRoleByUserId(int userId);
}
