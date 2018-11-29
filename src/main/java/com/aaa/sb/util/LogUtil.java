package com.aaa.sb.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * className:LogUtil
 * discription:
 * author:zz
 * createTime:2018-11-23 18:01
 */
@Component
@Aspect
public class LogUtil {
    /**
     * 切入点配置
     */
    @Pointcut(value = "execution(* com.aaa.sb.service.*.*(..))")
    public void pointCutOne() {

    }

    /**
     * @param joinPoint
     */
    @Before(value = "pointCutOne()")
    public void beforeSaveLog(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "pointCutOne()")
    public void afterReturnSaveLog(JoinPoint joinPoint) {
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println(name + "..." + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "pointCutOne()", throwing = "e")
    public void afterThrowingSavelog(JoinPoint joinPoint, Exception e) {
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println(name + ".." + joinPoint.getSignature().getName() + ".." + name.getClass().getName() + ".." + e.getMessage());
    }

    @Before(value = "pointCutOne()")
    public void afterSaveLog(JoinPoint joinPoint) {
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println(name + ".." + joinPoint.getSignature().getName());
    }

    @Around(value = "pointCutOne()")
    public Object aroundSaveLog(ProceedingJoinPoint proceedingJoinPoint) {
        Object proceed = null;
        System.out.println(System.currentTimeMillis());

        try {
            String name = proceedingJoinPoint.getSignature().getName();
            System.out.println(name + ".." + proceedingJoinPoint.getSignature().getName());
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
        return proceed;
 }
}
