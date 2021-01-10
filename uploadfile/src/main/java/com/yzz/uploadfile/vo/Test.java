package com.yzz.uploadfile.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName Test
 * @Author yky
 * @Date 2021/1/10
 * @Version 1.0
 */
@Data
@Component
public class Test {

	@Value("${aliyun.sdk}")
	String value;
}
