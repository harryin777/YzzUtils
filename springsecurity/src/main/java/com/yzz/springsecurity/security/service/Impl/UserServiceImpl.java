package com.yzz.springsecurity.security.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.springsecurity.security.dao.UserDao;
import com.yzz.springsecurity.security.entity.Role;
import com.yzz.springsecurity.security.vo.UserVO;
import com.yzz.springsecurity.security.service.UserService;
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
