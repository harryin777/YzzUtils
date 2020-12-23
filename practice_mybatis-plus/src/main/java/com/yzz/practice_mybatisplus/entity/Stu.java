package com.yzz.practice_mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu implements Serializable {

	@TableId(type = IdType.ID_WORKER)
	@NotNull(message = "id不能为空")
	private Long id;
	
	@Size(max = 10, message = "姓名长度不能超过10")
	private String name;
	
	@Min(value = 5, message = "不能小于5岁")
	@Max(value = 25, message = "不能超过25岁")
	private int age;
	
	@NotNull(message = "id不能为空")
	private String gender;
}
