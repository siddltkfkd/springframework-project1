package com.nhnacademy.edu.springframework.project.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class JavaAspect {
    @Around("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..))")
    public Object printExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try{
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            log.info("[{}].[{}] {}ms", pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName(), stopWatch.getTotalTimeMillis());
            log.info(stopWatch.prettyPrint());
        }
    }

}
