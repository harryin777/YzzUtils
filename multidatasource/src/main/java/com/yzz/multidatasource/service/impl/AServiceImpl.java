package com.yzz.multidatasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.multidatasource.annotation.UsingDataSource;
import com.yzz.multidatasource.dao.ADao;
import com.yzz.multidatasource.entity.A;
import com.yzz.multidatasource.myenum.DataSourceEnum;
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

	@UsingDataSource(value = DataSourceEnum.PROD_A)
	@Override
	public A getOne(ThreadLocal threadLocal) {
		return aDao.getOne();
	}
}
