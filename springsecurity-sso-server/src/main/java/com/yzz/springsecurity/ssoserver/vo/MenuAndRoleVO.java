package com.yzz.springsecurity.ssoserver.vo;

import com.yzz.springsecurity.ssoserver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MenuAndRole
 * @Author yky
 * @Date 2021/3/10
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuAndRoleVO implements Serializable {
	
	private int menuId;
	
	private String urlPattern;
	
	private List<Role> roleList;
}
