package com.priv.handler;

import com.priv.constant.TraceConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Json
 * @date 2021/6/9 9:43
 */
@Aspect
@Component
public class LogMdcAspect {


    @Around(value = "@annotation(org.springframework.scheduling.annotation.Async)")
    public Object insertTraceId(ProceedingJoinPoint joinPoint) throws Throwable {
        MDC.put(TraceConstant.MDC_TRACE, UUID.randomUUID().toString().replace("-", ""));
        Object result = joinPoint.proceed();
        MDC.remove(TraceConstant.MDC_TRACE);
        return result;
    }

}
