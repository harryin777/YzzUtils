package com.yzz.springsecurity.security.service;

import com.yzz.springsecurity.security.entity.Role;

import java.util.List;

/**
 * @ClassName MenuService
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
public interface MenuService {
	
	List<Role> getRoleByMenuId(int menuId);
}
