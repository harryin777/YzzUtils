package com.yzz.hub.utils;

import cn.hutool.json.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DescartesUtils
 * @Author yky
 * @Date 2021/3/19
 * @Version 1.0
 */
public class DescartesUtils {
	
	private static void descartes(List<List<String>> dimvalue, List<List<String>> result, int layer, List<String> curList) {
		if (layer < dimvalue.size() - 1) {
			if (dimvalue.get(layer).size() == 0) {
				descartes(dimvalue, result, layer + 1, curList);
			} else {
				for (int i = 0; i < dimvalue.get(layer).size(); i++) {
					List<String> list = new ArrayList<String>(curList);
					list.add(dimvalue.get(layer).get(i));
					descartes(dimvalue, result, layer + 1, list);
				}
			}
			//这里直接这样处理，可以少一次递归，然后其他不知道好在哪
		} else if (layer == dimvalue.size() - 1) {
			if (dimvalue.get(layer).size() == 0) {
				result.add(curList);
			} else {
				for (int i = 0; i < dimvalue.get(layer).size(); i++) {
					List<String> list = new ArrayList<String>(curList);
					list.add(dimvalue.get(layer).get(i));
					result.add(list);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> listSub1 = new ArrayList<String>();
		List<String> listSub2 = new ArrayList<String>();
		List<String> listSub3 = new ArrayList<String>();
		listSub1.add("1");
		listSub1.add("2");
		
		listSub2.add("3");
		listSub2.add("4");
		
		listSub3.add("a");
		listSub3.add("b");
		
		list.add(listSub1);
		list.add(listSub2);
		list.add(listSub3);
		List<List<String>> result = new ArrayList<List<String>>();
		descartes(list, result, 0, new ArrayList<String>());
		System.out.println(result);
	}
}
