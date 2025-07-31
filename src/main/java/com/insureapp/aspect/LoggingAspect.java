package com.insureapp.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

	@Pointcut("execution(* com.insureapp.controller..*(..))")
	public void controllerMethods() {}
	
	@Around("controllerMethods()")
	public Object logControllerExecution(ProceedingJoinPoint proceedingjoinpoint) throws Throwable {
		                  Signature signature=      proceedingjoinpoint.getSignature();
		                String className=  signature.getDeclaringType().getSimpleName();
		                String methodname=   signature.getName();
		                Object[] args=proceedingjoinpoint.getArgs();
		                long startTime = System.currentTimeMillis();
		                log.info("➡️ Entering [{}#{}] with args: {}", className, methodname, Arrays.toString(args));
		                
		                Object result=proceedingjoinpoint.proceed();

		                long endTime = System.currentTimeMillis();
		                log.info("⬅️ Exiting [{}#{}]; execution time: {} ms", className, methodname, (endTime - startTime));
		                return result;
	}
}
