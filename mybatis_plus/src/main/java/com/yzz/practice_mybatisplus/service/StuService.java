package com.yzz.practice_mybatisplus.service;

import com.yzz.hub.vo.ResultVO;
import com.yzz.practice_mybatisplus.entity.Stu;

import java.util.List;

public interface StuService  {
	
	List<Stu> getAll();
	
	Stu getOne(int id);
	
	Integer updateAfterSelect(Long id, Stu stu);
	
	ResultVO selectBatch(int pageCur, int pageSize);

	Integer insertStu(Stu stu);
}
