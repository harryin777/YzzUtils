package com.yzz.springsecurity.ssoserver.config.securityconfig;

import com.yzz.hub.utils.RedisUtils;
import com.yzz.springsecurity.ssoserver.dao.MenuDao;
import com.yzz.springsecurity.ssoserver.entity.Role;
import com.yzz.springsecurity.ssoserver.service.MenuService;
import com.yzz.springsecurity.ssoserver.vo.MenuAndRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName MyFilterInvocationSecurityMetadataSource
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Component
@Slf4j
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	@Resource
	private MenuDao menuDao;
	
	@Resource
	private MenuService menuService;
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		
		//所有菜单以及对应的权限从redis中获取
		Map<Object, Object> menuRoleMappingMap =
				 RedisUtils.hGetAll("menu_role");
		
		if(menuRoleMappingMap == null){
			//数据库查询所有菜单以及对应的权限
			List<MenuAndRoleVO> menuAndRoleVOList = menuService.getAllMenusRoles();
			menuRoleMappingMap = new HashMap<>();
			for (int i = 0; i < menuAndRoleVOList.size(); i++) {
				List<Role> roleList =
						(List<Role>) menuRoleMappingMap.getOrDefault(menuAndRoleVOList.get(i), new ArrayList<>());
				roleList.addAll(menuAndRoleVOList.get(i).getRoleList());
				//以url作为key，放进map
				RedisUtils.hPut("menu_role", menuAndRoleVOList.get(i).getUrlPattern(), roleList);
			}
			
		}
		
		List<Role> targetRoles = (List<Role>) menuRoleMappingMap.getOrDefault(requestUrl, null);
		
		if(targetRoles != null){
			String[] roleArr = new String[targetRoles.size()];
				for (int i = 0; i < roleArr.length; i++) {
					roleArr[i] = targetRoles.get(i).getRoleName();
				}
				return SecurityConfig.createList(roleArr);
		}
		/**
		 * 下面方式使用了antmatcher，这个更合理，但是使用了antmatcher没法做到O（1）时间内获取到
		 *  url对应的权限，只能在O（n）时间内
		 */
		//所有的菜单查询以后放入redis
//		List<Menu> allMenus = (List<Menu>) RedisUtils.hGet("1", "menuList");
//		if(allMenus == null){
//			allMenus = menuDao.selectList(new QueryWrapper<Menu>());
//			RedisUtils.hPut("1", "menuList", allMenus);
//		}
//		for (Menu menu : allMenus) {
//			if (antPathMatcher.match(menu.getUrlPattern(), requestUrl)) {
//				List<Role> roles = menuService.getRoleByMenuId(menu.getId());
//				String[] roleArr = new String[roles.size()];
//				for (int i = 0; i < roleArr.length; i++) {
//					roleArr[i] = roles.get(i).getRoleName();
//				}
//				return SecurityConfig.createList(roleArr);
//			}
//		}
		
		//这里赋予的是登录以后的权限，也就是如果一个人没有配置任何权限，那么他登录以后会获得这个权限
		return SecurityConfig.createList("ROLE_LOGIN");
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}
