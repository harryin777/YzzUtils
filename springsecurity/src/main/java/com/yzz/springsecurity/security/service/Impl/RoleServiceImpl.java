package com.yzz.springsecurity.security.service.Impl;

import com.yzz.springsecurity.security.dao.RoleDao;
import com.yzz.springsecurity.security.entity.Role;
import com.yzz.springsecurity.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;
	
	@Override
	public List<Role> getRoleByUserId(int userId) {
		
		return null;
	}
}
