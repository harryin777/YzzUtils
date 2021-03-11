package com.yzz.springsecurity.ssoserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzz.springsecurity.ssoserver.entity.Role;
import com.yzz.springsecurity.ssoserver.entity.Menu;
import com.yzz.springsecurity.ssoserver.vo.MenuAndRoleVO;


import java.util.List;

/**
 * @ClassName MenuDao
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
public interface MenuDao extends BaseMapper<Menu> {
	
	List<Role> getRoleByMenuId(int menuId);
	
	List<MenuAndRoleVO> getAllMenusRoles();
}
