package com.yzz.practice_mybatisplus.service;

import com.yzz.practice_mybatisplus.entity.Stu;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StuService  {
	
	List<Stu> getAll();
	
	Stu getOne(int id);
}
