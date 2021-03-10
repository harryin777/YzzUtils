package com.yzz.springsecurity.security.service.Impl;

import com.yzz.springsecurity.security.dao.MenuDao;
import com.yzz.springsecurity.security.entity.Role;
import com.yzz.springsecurity.security.service.MenuService;
import com.yzz.springsecurity.security.vo.MenuAndRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.SameLen;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuDao menuDao;
	
	@Override
	public List<Role> getRoleByMenuId(int menuId) {
		return menuDao.getRoleByMenuId(menuId);
	}
	
	@Override
	public List<MenuAndRoleVO> getAllMenusRoles() {
		return menuDao.getAllMenusRoles();
	}
}
