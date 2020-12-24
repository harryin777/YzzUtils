package com.yzz.practice_mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzz.practice_mybatisplus.DTO.ResultDTO;
import com.yzz.practice_mybatisplus.dao.StuDao;
import com.yzz.practice_mybatisplus.entity.Stu;
import com.yzz.practice_mybatisplus.service.StuService;
import com.yzz.practice_mybatisplus.vo.PageData;
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
	
	@Override
	public Integer updateAfterSelect(Long id, Stu stu) {
		Stu stuOld = stuDao.selectById(id);
		stuOld.setAge(stu.getAge());
		stuOld.setName(stu.getName());
		stuOld.setGender(stu.getGender());
		stuOld.setId(stu.getId());
		return stuDao.updateById(stuOld);
	}
	
	@Override
	public ResultDTO selectBatch(int pageCur, int pageSize) {
		PageHelper.startPage(pageCur, pageSize);
//		List<Stu> listStu = stuDao.selectList(new QueryWrapper<>());
		List<Stu> listStu = stuDao.getAll();
		PageInfo<Stu> result = new PageInfo<>(listStu);
		long total = result.getTotal();
		int pages = result.getPages();
		PageData pageData = new PageData();
		pageData.put("total",total);
		pageData.put("pages", pages);
		pageData.put("currentPage", result.getPageNum());
		pageData.put("list", listStu);
		
		return new ResultDTO(true, 200, "查询成功", pageData);
	}
}
