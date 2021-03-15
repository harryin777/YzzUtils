package com.yzz.multidatasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.multidatasource.dao.CDao;
import com.yzz.multidatasource.entity.C;
import com.yzz.multidatasource.service.CService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName CServiceImpl
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
@Service
public class CServiceImpl implements CService {
	
	@Resource
	private CDao cDao;
	
	@Override
	public C getOne() {
		return cDao.selectOne(new QueryWrapper<C>());
	}
}
