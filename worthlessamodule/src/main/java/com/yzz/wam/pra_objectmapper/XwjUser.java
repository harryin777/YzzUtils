package com.yzz.wam.pra_objectmapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.checkerframework.common.reflection.qual.GetConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName XwjUser
 * @Author yky
 * @Date 2021/1/18
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class XwjUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String message;
	
	private Date sendTime;
	
	// 这里手写字母大写，只是为了测试使用，是不符合java规范的
	private String NodeName;
	
	private List<Integer> intList;
	
	public XwjUser(int id, String msg, Date date){
		this.id = id;
		this.message = msg;
		this.sendTime = date;
	}
}
