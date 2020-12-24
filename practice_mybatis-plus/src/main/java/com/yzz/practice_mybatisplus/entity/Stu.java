package com.yzz.practice_mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu implements Serializable {
	
	@TableId( type = IdType.AUTO)
	private Long id;
	
	@Size(max = 10, message = "姓名长度不能超过10")
	private String name;
	
	@Min(value = 5, message = "不能小于5岁")
	@Max(value = 85, message = "不能超过25岁")
	private int age;
	
	@NotNull(message = "id不能为空")
	private String gender;

	@TableField(value = "insert_time", fill = FieldFill.INSERT)
	private Date insertTime;
	
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	
	@Version
	@TableField(value = "version", fill = FieldFill.INSERT)
	private Integer version;
}
