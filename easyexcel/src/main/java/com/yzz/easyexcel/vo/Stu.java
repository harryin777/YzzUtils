package com.yzz.easyexcel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Stu
 * @Author yky
 * @Date 2021/1/4
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu {
	/**
	 * 写excel的时候不需要加index，读的时候需要加
	 */
	@ExcelProperty(value = "学生编号",index = 0)
	private int no;
	
	@ExcelProperty(value = "学生姓名", index = 1)
	private String name;
}
