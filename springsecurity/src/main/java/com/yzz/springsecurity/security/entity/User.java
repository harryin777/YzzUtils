package com.yzz.springsecurity.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_user")
public class User implements Serializable {
	
	private int id;
	
	private String username;
	
	private String password;
}
