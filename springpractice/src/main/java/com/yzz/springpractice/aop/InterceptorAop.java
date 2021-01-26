package com.yzz.springpractice.aop;

import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

/**
 * @ClassName InterceptorAop
 * @Author yky
 * @Date 2021/1/25
 * @Version 1.0
 */
public class InterceptorAop {
	
	/**
	 * 前置通知
	 */
	public void doBefore() {
		System.out.println("========doBefore advice==========");
	}
	
	/**
	 * 返回后通知
	 */
	public void doAferReturning() {
		System.out.println("========sdoAferReturning advice================");
	}
	
	/**
	 * 后置通知
	 */
	public void doAfter() {
		System.out.println("========doAfter advice==========");
	}
	
	/**
	 * 抛出异常后通知
	 */
	public void doAferThrowing() {
		System.out.println("=========doAferThrowing advice================");
	}
	
	/**
	 * 环绕通知 环绕通知的第一个参数必须是MethodInvocationProceedingJoinPoint pjp.proceed()执行原业务方法
	 *
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
//	public Object doAround(MethodInvocationProceedingJoinPoint pjp) throws Throwable {
//		System.out.println("=========doAround start===========");
//		Object result = pjp.proceed();
//		System.out.println("==========doAround end==========");
//		return result;
//	}
}
