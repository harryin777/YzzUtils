package com.yzz.practice_mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start autofill insertTime...");
		this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
		log.info("end autofill insertTime...");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start autofill updateTime...");
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
		log.info("end autofill updateTime...");
	}
}
