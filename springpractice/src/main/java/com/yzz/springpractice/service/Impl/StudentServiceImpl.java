package com.yzz.springpractice.service.Impl;

import com.yzz.springpractice.entity.Student;
import com.yzz.springpractice.service.StuService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName StudentServiceImpl
 * @Author yky
 * @Date 2021/1/25
 * @Version 1.0
 */
@Service
public class StudentServiceImpl implements StuService {
	@Override
	public Student getOne(String id) {
		System.out.println("id" + id);
		return null;
	}
}
