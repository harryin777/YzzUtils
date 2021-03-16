package com.yzz.multidatasource.aop;

import com.yzz.multidatasource.annotation.UsingDataSource;
import com.yzz.multidatasource.config.DataSourceContextHolder;
import com.yzz.multidatasource.myenum.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName DataSourceUsingAspect
 * @Author yky
 * @Date 2021/3/15
 * @Version 1.0
 */
@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceUsingAspect {

	// mapper包下所有包，下所有类，所有方法，参数任意，返回值任意
	@Pointcut("execution(* com.yzz.multidatasource.service.impl.*.*(..))")
	public void pointCut() {

	}
	/**
	 * 说明：
	 * 1.注解不能加在Mapper上面。因为Mybatis中默认的方法不能被获取到。
	 * 2.可以吧注解加在到service层上，每个方法上都带有切面数据库的值。
	 * 但是所有的查询语句必须要写到service这一层，不能直接在controller中调用mybatisplus在service中中默认的方法。
	 * 例如直接在controller中调用：  laborService.list() ==》这个是不会被切面监察到的。
	 *
	 * 注解逻辑：
	 *  如果类上有注解，直接使用类的注解，不管方法上是否存在注解。
	 *  如果类上没有注解，使用方法上的注解。
	 *  如果类和方法上都没有注解，使用默认的数据源
	 */
	//前置通知，设置数据源
	@Before("pointCut()")
	public void doBefore(JoinPoint point) throws ClassNotFoundException {
		log.info("进入前置通知");
		//获取接口或者类的名称
		String cName = point.getSignature().getDeclaringType().getSimpleName();

		//获取到了这个方法的名称
		String mName = point.getSignature().getName();

		String declaringTypeName = point.getSignature().getDeclaringTypeName();
		//获取到了类的全路径
		Class<?> aClass = Class.forName(declaringTypeName);

		//获取类上的注解
		UsingDataSource annotation = aClass.getAnnotation(UsingDataSource.class);
		if (annotation == null) {
			//获取所有的方法
			Method[] methods = aClass.getMethods();
			for (int i = 0; i < methods.length; i++) {
				//找到该方法名对应的method，获取method上的注解
				if (methods[i].getName().equals(mName)) {
					UsingDataSource mAnno = methods[i].getAnnotation(UsingDataSource.class);
					if (mAnno == null) {
						DataSourceContextHolder.setDataSource(DataSourceEnum.DEFAULT.getValue());
					} else {
						DataSourceContextHolder.setDataSource(mAnno.value().getValue());
					}
					break;
				}
			}
		} else {
			//类上的注解不为空
			DataSourceContextHolder.setDataSource(annotation.value().getValue());
		}
	}

	//后置通知，把数据源清除
	@After("pointCut()")
	public void doAfter() {
		log.info("进入后置通知");
		DataSourceContextHolder.clear();
	}
}
