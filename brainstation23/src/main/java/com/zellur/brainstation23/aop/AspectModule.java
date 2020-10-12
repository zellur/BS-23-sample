package com.zellur.brainstation23.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AspectModule {
    @Before("execution( * com.zellur.brainstation23.repository.*.*(..))")
    public void doBeforeExecuteAnyRepositoryMethod(JoinPoint joinPoint) {
        log.info("Before repository method {} called. Method arguments were {}" , joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(pointcut = "execution( * com.zellur.brainstation23.repository.*.*(..))", returning = "result")
    public void doAfterReturnTheResultOfRepositoryMethod(JoinPoint joinPoint, Object result) {
        log.info("Returned from repository method: {} Returned {} ",joinPoint.getSignature().getName(), result == null? "null" : result.toString());
    }
    @Before("execution( * com.zellur.brainstation23.service.*.*(..))")
    public void doBeforeExecuteAnyServiceMethod(JoinPoint joinPoint) {
        log.info("Before service method {} called. Method arguments were {}" , joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(pointcut = "execution( * com.zellur.brainstation23.service.*.*(..))", returning = "result")
    public void doAfterReturnTheResultOfServiceMethod(JoinPoint joinPoint, Object result) {
        log.info("Returned from service method: {} Returned {} ",joinPoint.getSignature().getName(), result == null? "null" : result.toString());
    }
    @Before("execution( * com.zellur.brainstation23.controller.*.*(..))")
    public void doBeforeExecuteAnyControllerMethod(JoinPoint joinPoint) {
        log.info("Before controller method {} called. Method arguments were {}" , joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(pointcut = "execution( * com.zellur.brainstation23.controller.*.*(..))", returning = "result")
    public void doAfterReturnTheResultOfControllerMethod(JoinPoint joinPoint, Object result) {
        log.info("Returned from controller method: {} Returned {} ",joinPoint.getSignature().getName(), result == null? "null" : result.toString());
    }

}
