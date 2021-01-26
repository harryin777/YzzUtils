package com.yzz.springpractice.controller;

import com.yzz.springpractice.entity.Student;
import com.yzz.springpractice.service.Impl.StudentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @ClassName controller
 * @Author yky
 * @Date 2021/1/21
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Controller
public class StuController {
	
	@Autowired
	Student student;
	
	@Autowired
	StudentServiceImpl studentServiceImpl;
	
}
