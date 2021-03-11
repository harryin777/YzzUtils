package com.yzz.springsecurity.ssoserver.config.securityconfig;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.springsecurity.ssoserver.dao.MenuDao;
import com.yzz.springsecurity.ssoserver.entity.Menu;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName AuthorityService
 * @Author yky
 * @Date 2021/3/10
 * @Version 1.0
 *
 * 这个类是用来替代decisionmanager和securitymetadatasource的
 */
@Component("AuthorityService")
public class AuthorityService {
	
	@Resource
	private MenuDao menuDao;
	
	public boolean hasPermission(HttpServletRequest request, Authentication authentication){
		
		// 获取请求的url
		String requestUrl = request.getRequestURI();
		// 获取全部的菜单，这一步可以直接存在redis里，第一次启动的时候
		// 因为每次都需获取而且基本不会变化
		List<Menu> menuList = menuDao.selectList(new QueryWrapper<Menu>());
		
		return false;
	}
}
