package com.yzz.springsecurity.ssoserver.service;



import com.yzz.springsecurity.ssoserver.entity.Role;
import com.yzz.springsecurity.ssoserver.vo.MenuAndRoleVO;

import java.util.List;

/**
 * @ClassName MenuService
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
public interface MenuService {
	
	List<Role> getRoleByMenuId(int menuId);
	
	List<MenuAndRoleVO> getAllMenusRoles();
}
