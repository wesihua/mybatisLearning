package com.wei.manager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 注解方式的AOP测试
 * @author weisihua
 *
 */
@Aspect
public class TestAspect {

	//pointcut
	/**
	 * 测试切入点
	 */
	@Pointcut("execution(* com.wei.manager.service.*.pring(..))")
	public void testCall(){}
	
	//advice
	@Before("testCall()")
	public void testAdvice(JoinPoint point){
		System.out.println("开始拦截了。。。。。");
	}
	
	@AfterReturning("testCall()")
	public void testAdviceAfter(JoinPoint point){
		System.out.println("结束拦截了。。。。。。。。");
	}
}
