package com.yzz.multidatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName MultipleDataSource
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSource();
	}
}
