package com.yzz.hub.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @ClassName SerializeUtils
 * @Author yky
 * @Date 2021/3/10
 * @Version 1.0
 */
public class SerializeUtils {
	/*
	 * 序列化
	 * */
	public static byte[] serizlize(Object object){
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(baos != null){
					baos.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	/*
	 * 反序列化
	 * */
	public static Object deserialize(byte[] bytes){
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try{
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(bais != null){
					bais.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
}
