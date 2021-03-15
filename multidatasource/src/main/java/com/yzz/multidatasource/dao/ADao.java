package com.yzz.multidatasource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzz.multidatasource.entity.A;

/**
 * @ClassName ADao
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
public interface ADao extends BaseMapper<A> {

	A getOne();
}
