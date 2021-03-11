package com.yzz.springsecurity.ssoserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Menu
 * @Author yky
 * @Date 2021/3/9
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("menu")
public class Menu implements Serializable {

	private int id;
	
	private String urlPattern;
}
