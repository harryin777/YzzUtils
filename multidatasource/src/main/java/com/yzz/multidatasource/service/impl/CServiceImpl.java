package com.yzz.multidatasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.multidatasource.annotation.UsingDataSource;
import com.yzz.multidatasource.dao.CDao;
import com.yzz.multidatasource.entity.C;
import com.yzz.multidatasource.myenum.DataSourceEnum;
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

	@UsingDataSource(value = DataSourceEnum.PROD_C)
	@Override
	public C getOne() {
		return cDao.getOne();
	}
}
