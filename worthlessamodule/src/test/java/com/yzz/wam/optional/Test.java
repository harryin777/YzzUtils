package com.yzz.wam.optional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName Test
 * @Author yky
 * @Date 2021/5/12
 * @Version 1.0
 */
public class Test {
	
	@org.junit.jupiter.api.Test
	void init(){
		
		System.out.println(testOptional("list"));
		System.out.println(testOptional(null));
	}
	
	List<?> testOptional(String str){
		List<String> list = new ArrayList<String>(){{
			add("iii");
			add("abc");
			add("efs");
		}};
		//作用是如果str不为空，执行后面的语句
		Optional.ofNullable(str).ifPresent(e -> list.sort(Comparator.naturalOrder()));
		return list;
	}
}
