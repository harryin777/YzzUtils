package com.yzz.practice_mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzz.practice_mybatisplus.entity.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 继承basemapper以后，可以有一些基本的方法，curd，不需要再写mapper文件
 */
@Repository
@Mapper
public interface StuDao extends BaseMapper<Stu> {
	
	List<Stu> getAll();

}
