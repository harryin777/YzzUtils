package com.yzz.multidatasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.multidatasource.dao.ADao;
import com.yzz.multidatasource.entity.A;
import com.yzz.multidatasource.service.AService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName AServiceImpl
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
@Service
public class AServiceImpl implements AService {
	
	@Resource
	private ADao aDao;
	
	@Override
	public A getOne() {
		return aDao.selectOne(new QueryWrapper<A>());
	}
}
