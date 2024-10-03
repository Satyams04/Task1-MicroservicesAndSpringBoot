package com.centime.microservices.serviceB.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Advice that logs before each controller method execution
    @Before("execution(* com.centime.microservices.serviceB.controller.ServiceBController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Trace ID: {} - Entering method: {}", String.valueOf(System.currentTimeMillis()), joinPoint.getSignature().getName());
        logger.info("Trace ID: {} - Arguments: {}", String.valueOf(System.currentTimeMillis()), joinPoint.getArgs());
    }
}

