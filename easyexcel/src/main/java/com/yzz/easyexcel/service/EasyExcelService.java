package com.yzz.easyexcel.service;

import com.yzz.easyexcel.vo.Stu;

import java.util.List;

public interface EasyExcelService {
	
	List<Stu> readData();
	
	boolean writeData();
}
