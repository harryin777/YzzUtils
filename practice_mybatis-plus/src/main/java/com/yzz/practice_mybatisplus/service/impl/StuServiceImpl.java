package com.yzz.practice_mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.practice_mybatisplus.dao.StuDao;
import com.yzz.practice_mybatisplus.entity.Stu;
import com.yzz.practice_mybatisplus.service.StuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {
	
	@Resource
	private StuDao stuDao;
	
	@Override
	public List<Stu> getAll() {
		return stuDao.getAll();
	}
	
	@Override
	public Stu getOne(int id) {
		QueryWrapper<Stu> qw = new QueryWrapper<Stu>();
		qw.eq("id", id);
		return stuDao.selectOne(qw);
	}
}
