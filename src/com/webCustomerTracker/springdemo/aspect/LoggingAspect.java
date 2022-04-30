package com.webCustomerTracker.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	//setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	
		//FOR CONTROLLER PACKAGE
	@Pointcut("execution(* com.webCustomerTracker.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
				
	}
	
		//FOR SERVICE PACKAGE
	@Pointcut("execution(* com.webCustomerTracker.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
		//FOR DAO PACKAGE
	@Pointcut("execution(* com.webCustomerTracker.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}

	
	//combine declarations
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {
		
	}
	
	//add @Before advice
	@Before("forAppFlow()")
	private void beforeAdvice(JoinPoint joinPoint) {
		//display method we are calling
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("@Before >>>> The method is " + method);
		
		//display the arguments to the method
		Object[] args = joinPoint.getArgs();
		
		for (Object arg : args) {
			myLogger.info("BEFORE ADVICE: ARGUMENT= " + arg);
		}
		
	}
	
	//add @AfterReturning advice
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "result")
	private void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		//display method we are calling
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("@AfterReturning >>>>>> The method is " + method);
		
		//display the returned data
		myLogger.info("RESULT::::>>>> " + result);
		
	}
	
	
	
}
