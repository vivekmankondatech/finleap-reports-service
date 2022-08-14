/**
 * 
 */
package com.finleap.app.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.finleap.app.common.util.CommonConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Any License information can go here
 */

/**
 * 
 * com.finleap.app.common.aspect | finleap-reports-service
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 2 Apr 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		2 Apr 2022						Initial commit
 * 
 * </pre>
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

	/**
	 * Pointcut that matches all Spring beans in the application's main packages.
	 */
	@Pointcut("within(com.finleap.app..*)")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	/**
	 * Pointcut that matches all repositories, services and Web REST endpoints.
	 */
	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}

	@Before("applicationPackagePointcut()")
	public void logMethodEntry(JoinPoint joinPoint) {

		printLogInfo(joinPoint, CommonConstants.LOG.ENTRY);
	}

	@Around("applicationPackagePointcut()")
	public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		final StopWatch stopWatch = new StopWatch();

		// calculate method execution time
		stopWatch.start();
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();

		// Log method execution time
		log.info(CommonConstants.LOG.EXECUTION_TIME, methodSignature.getName(),
				methodSignature.getDeclaringType().getSimpleName(), stopWatch.getTotalTimeMillis());

		return result;
	}

	@Before("applicationPackagePointcut()")
	public void logMethodExit(JoinPoint joinPoint) {

		printLogInfo(joinPoint, CommonConstants.LOG.EXIT);
	}

	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e         exception
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		log.error(CommonConstants.LOG.ERROR, methodSignature.getName(),
				methodSignature.getDeclaringType().getSimpleName(), e.getMessage());
	}

	/**
	 * Print Log Info
	 * 
	 * @param joinPoint
	 * @param infoFormat
	 * @return
	 * @throws Throwable
	 */
	private void printLogInfo(JoinPoint joinPoint, String infoFormat) {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		log.info(infoFormat, methodSignature.getName(), methodSignature.getDeclaringType().getSimpleName());

	}
}
