package com.yzz.multidatasource.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yzz.multidatasource.myenum.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyConfiguration
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
@Slf4j
@Configuration
@MapperScan("com.yzz.multidatasource.dao")
public class MyConfiguration {
	
	@Bean(name = "defaultDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource getDefaultDataSource() {
		log.info("defaultDataSource");
		return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
	}
	
	@Bean(name = "prodADataSource")
	@ConfigurationProperties(prefix = "mdb.datasource.a")
	public DataSource getProdADataSource() {
		log.info("prodADataSource");
		return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
	}
	
	@Bean(name = "prodBDataSource")
	@ConfigurationProperties(prefix = "mdb.datasource.b")
	public DataSource getProdBDataSource() {
		log.info("prodBDataSource");
		return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
	}
	
	@Bean(name = "prodCDataSource")
	@ConfigurationProperties(prefix = "mdb.datasource.c")
	public DataSource getProdCDataSource() {
		log.info("prodCDataSource");
		return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
	}
	
	@Bean
	@Primary
	public DataSource multipleDataSource(@Qualifier("defaultDataSource") DataSource defaultDataSource,
	                                     @Qualifier("prodADataSource") DataSource prodADataSource,
	                                     @Qualifier("prodBDataSource") DataSource prodBDataSource,
	                                     @Qualifier("prodCDataSource") DataSource prodCDataSource) {
		MultipleDataSource multipleDataSource = new MultipleDataSource();
		Map< Object, Object > targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceEnum.DEFAULT.getValue(), defaultDataSource);
		targetDataSources.put(DataSourceEnum.PROD_A.getValue(), prodADataSource);
		targetDataSources.put(DataSourceEnum.PROD_B.getValue(), prodBDataSource);
		targetDataSources.put(DataSourceEnum.PROD_C.getValue(), prodCDataSource);
		
		//添加数据源
		multipleDataSource.setTargetDataSources(targetDataSources);
		//设置默认数据源
		multipleDataSource.setDefaultTargetDataSource(defaultDataSource);
		return multipleDataSource;
	}
	
	
	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(multipleDataSource(getDefaultDataSource(),getProdADataSource(),
				getProdBDataSource(),getProdCDataSource()));
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**/*Mapper.xml"));
		
		
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCacheEnabled(false);
		sqlSessionFactory.setConfiguration(configuration);
		
		return sqlSessionFactory.getObject();
	}
}
