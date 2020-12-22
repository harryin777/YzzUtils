package com.yzz.practice_mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu implements Serializable {

	@TableId(type = IdType.ID_WORKER)
	private Long id;
	private String name;
	private int age;
	private String gender;
}
