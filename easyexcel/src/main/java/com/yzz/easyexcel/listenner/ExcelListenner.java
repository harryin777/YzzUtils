package com.yzz.easyexcel.listenner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yzz.easyexcel.vo.Stu;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @ClassName ExcelListenner
 * @Author yky
 * @Date 2021/1/4
 * @Version 1.0
 */
@Slf4j
public class ExcelListenner extends AnalysisEventListener<Stu> {
	
	/**
	 * 读取操作
	 * @param stu
	 * @param analysisContext
	 */
	@Override
	public void invoke(Stu stu, AnalysisContext analysisContext) {
		log.info("读取的内容: {}", stu);
	}
	
	/**
	 * 读取表头
	 * @param headMap
	 * @param context
	 */
	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		for(Map.Entry entry : headMap.entrySet()){
			log.info("key:{}", entry.getKey());
			log.info("value:{}", entry.getValue());
		}
	}
	
	/**
	 * 读取结束以后的操作
	 * @param analysisContext
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		log.info("读取完成");
	}
}
