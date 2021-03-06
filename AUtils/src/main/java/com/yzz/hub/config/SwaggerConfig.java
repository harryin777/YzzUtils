package com.yzz.hub.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ServiceBaseConfig
 * @Author yky
 * @Date 2020/12/25
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket webApiConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("webApi")
				.apiInfo(webApiInfo())
				.select()
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build();

	}

	private ApiInfo webApiInfo(){
		return new ApiInfoBuilder()
				.title("云util API文档")
				.description("本文档描述了一系列demo")
				.version("1.0")
				.contact(new Contact("yzz", "http://yzz.com", "yzz@qq.com"))
				.build();
	}

}
