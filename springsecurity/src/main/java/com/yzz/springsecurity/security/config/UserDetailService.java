package com.yzz.springsecurity.security.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.hub.utils.RedisUtils;
import com.yzz.springsecurity.security.dao.MenuDao;
import com.yzz.springsecurity.security.dao.RoleDao;
import com.yzz.springsecurity.security.dao.UserDao;
import com.yzz.springsecurity.security.entity.Role;
import com.yzz.springsecurity.security.entity.User;
import com.yzz.springsecurity.security.service.RoleService;
import com.yzz.springsecurity.security.vo.UserVO;
import com.yzz.springsecurity.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserDetailService
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 */
@Slf4j
@Component
public class UserDetailService implements UserDetailsService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private UserService userService;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//redis获取到用户
		UserVO userVO = (UserVO) RedisUtils.getObject(username);
		
		if(userVO == null){
			User user = userDao.selectOne(new QueryWrapper<User>().eq("username", username));
			List<Role> roleList = userService.getRolesByUserId(user.getId());
			//注意这个地方的返回值，如果属性中的password不是用BCryptPasswordEncoder加密过的话，会报错
			userVO = new UserVO(user.getUsername(), user.getPassword(), roleList);
			RedisUtils.setObject(username, userVO);
		}
		
		return userVO;
	}
}
