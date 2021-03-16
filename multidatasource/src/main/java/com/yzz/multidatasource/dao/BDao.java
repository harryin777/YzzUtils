package com.yzz.multidatasource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzz.multidatasource.entity.A;
import com.yzz.multidatasource.entity.B;

/**
 * @ClassName BDao
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
public interface BDao extends BaseMapper<B> {

	B getOne();
}
