package com.yzz.wam.pra_objectmapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yzz.wam.WamApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName Test
 * @Author yky
 * @Date 2021/1/18
 * @Version 1.0
 */
public class MapperTest {
	
	public static ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	void init() {
		// 转换为格式化的json
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		// 如果json中有新增的字段并且是实体类类中不存在的，不报错
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@DisplayName("对象与json字符串、byte数组")
	@Test
	void testObj() throws JsonGenerationException, JsonMappingException, Exception {
		XwjUser user = new XwjUser(1, "Hello World", new Date());
		
		mapper.writeValue(new File("D:/test.txt"), user); // 写到文件中
		// mapper.writeValue(System.out, user); //写到控制台
		
		String jsonStr = mapper.writeValueAsString(user);
		System.out.println("对象转为字符串：" + jsonStr);
		
		byte[] byteArr = mapper.writeValueAsBytes(user);
		System.out.println("对象转为byte数组：" + byteArr);
		
		XwjUser userDe = mapper.readValue(jsonStr, XwjUser.class);
		System.out.println("json字符串转为对象：" + userDe);
		
		XwjUser useDe2 = mapper.readValue(byteArr, XwjUser.class);
		System.out.println("byte数组转为对象：" + useDe2);
	}
	
	@DisplayName("list集合与json字符串")
	@Test
	void testList() throws JsonGenerationException, JsonMappingException, IOException {
		List<XwjUser> userList = new ArrayList<>();
		userList.add(new XwjUser(1, "aaa", new Date()));
		userList.add(new XwjUser(2, "bbb", new Date()));
		userList.add(new XwjUser(3, "ccc", new Date()));
		userList.add(new XwjUser(4, "ddd", new Date()));
		
		String jsonStr = mapper.writeValueAsString(userList);
		System.out.println("集合转为字符串：" + jsonStr);
		
		List<XwjUser> userListDes = mapper.readValue(jsonStr, List.class);
		System.out.println("字符串转集合：" + userListDes);
	}
	
	@DisplayName("map与json字符串")
	@Test
	void testMap() throws JsonProcessingException {
		Map<String, Object> testMap = new HashMap<>();
		testMap.put("name", "merry");
		testMap.put("age", 30);
		testMap.put("date", new Date());
		testMap.put("user", new XwjUser(1, "Hello World", new Date()));
		
		try {
			String jsonStr = mapper.writeValueAsString(testMap);
			System.out.println("Map转为字符串：" + jsonStr);
			try {
				Map<String, Object> testMapDes = mapper.readValue(jsonStr, Map.class);
				System.out.println("字符串转Map：" + testMapDes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@DisplayName("修改转换时的日期格式：")
	@Test
	public void testOther() throws IOException {
		// 修改时间格式
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		XwjUser user = new XwjUser(1, "Hello World", new Date());
		user.setIntList(Arrays.asList(1, 2, 3));
		
		String jsonStr = mapper.writeValueAsString(user);
		System.out.println("对象转为字符串：" + jsonStr);
	}


}
