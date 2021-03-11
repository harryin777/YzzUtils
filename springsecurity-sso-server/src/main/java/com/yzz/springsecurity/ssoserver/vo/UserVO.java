package com.yzz.springsecurity.ssoserver.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName User
 * @Author yky
 * @Date 2021/3/4
 * @Version 1.0
 *
 * 这个对象是包含了用户信息和用户权限信息，而这两个东西在数据库里一般是不存在一张表里的
 * 这里为了简便，就直接把用户信息和权限放在了一张表里
 * 所以这个对象应该是VO而不是entity，通常会有另外一个user，其属性和user表的列一一对应
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("uservo")
public class UserVO implements Serializable, UserDetails {
	
	private String username;
	
	private String password;
	
	private List<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}
	
	//是否未过期
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	//是否未锁定
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	//信用凭证是否未过期
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	//是否启用
	@Override
	public boolean isEnabled() {
		return true;
	}
}
