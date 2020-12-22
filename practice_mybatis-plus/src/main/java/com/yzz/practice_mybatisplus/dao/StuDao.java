package com.yzz.practice_mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzz.practice_mybatisplus.entity.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StuDao extends BaseMapper<Stu> {
}
