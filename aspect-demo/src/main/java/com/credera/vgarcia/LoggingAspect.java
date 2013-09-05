package com.credera.vgarcia;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut ( "execution(* com.credera.vgarcia.HomeController.*(..))" )
	public void logging() {}
	
	@Before ("logging()")
	public void logTestBefore() {
		logger.info("Performing before advice...");
	}
	
	@After ("logging()")
	public void logTestAfter() {
		logger.info("Performing after advice...");
	}
	
	@Around ("logging()")
	public String logTestAround(ProceedingJoinPoint joinpoint) {
		String result = null;
		
		try {
			logger.info("Starting call to " + joinpoint.getSignature().toShortString());
			long startTime = System.currentTimeMillis();
			
			result = joinpoint.proceed(joinpoint.getArgs()).toString();
			
			long endTime = System.currentTimeMillis();
			
			logger.info("Call to " + joinpoint.getSignature().toShortString() +
					" completed in " + String.valueOf(endTime - startTime)  + " milliseconds.");
		}
		catch (Throwable t) {
			logger.info("Something went wrong in " + joinpoint.getSignature().toShortString());
		}
		
		return result;
	}
	
}
