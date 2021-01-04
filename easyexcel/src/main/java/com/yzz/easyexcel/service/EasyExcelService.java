package com.yzz.easyexcel.service;

import com.yzz.easyexcel.vo.Stu;
import com.yzzutils.hub.dto.ResultDTO;

import java.util.List;

public interface EasyExcelService {
	
	List<Stu> readData();
	
	boolean writeData();
}
