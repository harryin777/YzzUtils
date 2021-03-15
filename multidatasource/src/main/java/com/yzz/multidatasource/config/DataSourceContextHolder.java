package com.yzz.multidatasource.config;

/**
 * @ClassName DataSourceContextHolder
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
public class DataSourceContextHolder {
	
	//每个线程独立拥有一个副本，每个线程都可以独立改变自己的副本。互相不影响
	private static final ThreadLocal<String> CONTEXT_HOLDER = new InheritableThreadLocal<>();
	/**
	 *  设置数据源
	 */
	public static void setDataSource(String db){
		CONTEXT_HOLDER.set(db);
	}
	/**
	 * 取得当前数据源
	 */
	public static String getDataSource(){
		return CONTEXT_HOLDER.get();
	}
	
	/**
	 * 清除上下文数据
	 */
	public static void clear(){
		CONTEXT_HOLDER.remove();
	}
}
