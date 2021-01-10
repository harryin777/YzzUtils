package com.yzz.uploadfile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UploadfileApplicationTests {

	@Resource
	private com.yzz.uploadfile.vo.Test test;

	@Test
	void contextLoads() {
		System.out.println(test.getValue());
	}

}
