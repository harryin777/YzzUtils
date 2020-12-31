package com.yzzutils.hub.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
//swagger3.0改成了这个注解@EnableOpenApi，不过2.0也可以用
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket webApiConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("webApi")
				.apiInfo(webApiInfo())
				.select()
				.paths(PathSelectors.any())
				.build();
		
	}
	
	private ApiInfo webApiInfo(){
		
		return new ApiInfoBuilder()
				.title("utils文档")
				.description("本文档描述了yzzutils")
				.version("1.0")
				.contact(new Contact("yzz", "http://yzz.com", "yzz@qq.com"))
				.build();
	}
}
