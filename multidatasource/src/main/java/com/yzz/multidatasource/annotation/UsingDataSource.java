package com.yzz.multidatasource.annotation;

import com.yzz.multidatasource.myenum.DataSourceEnum;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UsingDataSource {
	
	DataSourceEnum value() default DataSourceEnum.DEFAULT;
}
