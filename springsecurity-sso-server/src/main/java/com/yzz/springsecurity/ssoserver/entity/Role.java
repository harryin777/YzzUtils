package com.yzz.springsecurity.ssoserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @ClassName Role
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Role implements Serializable, GrantedAuthority  {
	
	private int id;
	
	private String roleName;
	
	@Override
	public String getAuthority() {
		return this.getRoleName();
	}
}
