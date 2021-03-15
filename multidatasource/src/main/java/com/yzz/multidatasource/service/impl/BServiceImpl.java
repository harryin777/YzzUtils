package com.yzz.multidatasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzz.multidatasource.dao.BDao;
import com.yzz.multidatasource.entity.B;
import com.yzz.multidatasource.service.BService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName BServiceImpl
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
@Service
public class BServiceImpl implements BService {
	
	@Resource
	private BDao bDao;
	
	@Override
	public B getOne() {
		return bDao.selectOne(new QueryWrapper<B>());
	}
}
