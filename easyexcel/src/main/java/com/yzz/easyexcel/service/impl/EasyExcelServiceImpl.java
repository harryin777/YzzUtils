package com.yzz.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.yzz.easyexcel.listenner.ExcelListenner;
import com.yzz.easyexcel.service.EasyExcelService;
import com.yzz.easyexcel.vo.Stu;
import com.yzzutils.hub.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EasyExcelServiceImpl
 * @Author yky
 * @Date 2021/1/4
 * @Version 1.0
 */
@Slf4j
@Service
public class EasyExcelServiceImpl implements EasyExcelService {
	/**
	 * 读取操作需要创建监听器
	 * @return
	 */
	@Override
	public List<Stu> readData() {
		List<Stu> result = new ArrayList<>();
		try{
			String fileName = "F:\\testStu.xlsx";
			AnalysisEventListener<Stu> stuAnalysisEventListener = new AnalysisEventListener<Stu>(){
				
				@Override
				public void invoke(Stu stu, AnalysisContext analysisContext) {
					result.add(stu);
				}
				
				@Override
				public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
					for(Map.Entry entry : headMap.entrySet()){
						log.info("key:{}", entry.getKey());
						log.info("value:{}", entry.getValue());
					}
				}
				
				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {
					log.info("读取完成");
				}
			};
			
			EasyExcel.read(fileName, Stu.class, stuAnalysisEventListener).sheet().doRead();
			
			
		}catch (Exception e){
			log.error("读取excel异常");
			log.error(e.fillInStackTrace().toString());
		}
		
		return result;
	}
	
	@Override
	public boolean writeData() {
		try{
			String fileName = "F:\\testStu.xlsx";
			Stu stu = new Stu(1, "小明");
			List<Stu> stuList = new ArrayList<>();
			stuList.add(stu);
			EasyExcel.write(fileName, Stu.class).sheet("学生列表").doWrite(stuList);
			return true;
		}catch (Exception e){
			log.error("写入excel异常");
			log.error(e.fillInStackTrace().toString());
		}
		return false;
	}
}
