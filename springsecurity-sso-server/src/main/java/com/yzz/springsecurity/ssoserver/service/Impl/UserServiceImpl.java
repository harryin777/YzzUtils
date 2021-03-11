package com.yzz.springsecurity.ssoserver.service.Impl;


import com.yzz.springsecurity.ssoserver.entity.Role;
import com.yzz.springsecurity.ssoserver.service.UserService;
import com.yzz.springsecurity.ssoserver.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	@Override
	public List<Role> getRolesByUserId(int userId) {
		return userDao.getRolesByUserId(userId);
	}
}
