package com.yzz.springsecurity.ssoserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzz.springsecurity.ssoserver.entity.Role;
import com.yzz.springsecurity.ssoserver.entity.User;

import java.util.List;

/**
 * @ClassName UserDao
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
public interface UserDao extends BaseMapper<User> {
	
	List<Role> getRolesByUserId(int userId);
}
