package com.yzz.practice_mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start autofill insertTime...");
		this.strictInsertFill(metaObject, "insertTime", Date.class, Calendar.getInstance().getTime());
		this.strictInsertFill(metaObject, "updateTime", Date.class, Calendar.getInstance().getTime());
		this.strictInsertFill(metaObject, "version", Integer.class, 1);
		log.info("end autofill insertTime...");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start autofill updateTime...");
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
		log.info("end autofill updateTime...");
	}
}
