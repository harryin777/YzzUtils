package com.yzz.springsecurity.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzz.springsecurity.security.entity.Role;
import com.yzz.springsecurity.security.entity.User;
import com.yzz.springsecurity.security.vo.UserVO;

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
