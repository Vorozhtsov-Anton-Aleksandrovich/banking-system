package com.aop;

import com.aop.annotation.HttpIncomeRequestLog;
import com.aop.annotation.HttpOutcomeRequestLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class HttpRequestLoggingAspect {

    private final ObjectMapper mapper = new ObjectMapper();

    @Before("@annotation(httpIncomeRequestLog)")
    public void logBefore(JoinPoint joinPoint, HttpIncomeRequestLog httpIncomeRequestLog) {
        Map<String, Object> logData = buildLogData(joinPoint, httpIncomeRequestLog.service());
        logMessage("Incoming request", logData);
    }

    @AfterReturning(pointcut = "@annotation(httpOutcomeRequestLog)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, HttpOutcomeRequestLog httpOutcomeRequestLog, Object result) {
        Map<String, Object> logData = buildLogData(joinPoint, httpOutcomeRequestLog.service());
        logData.put("result", result);
        logMessage("Outgoing response", logData);
    }

    private Map<String, Object> buildLogData(JoinPoint joinPoint, String service) {
        Map<String, Object> logData = new HashMap<>();
        logData.put("type", "INFO");
        logData.put("timestamp", Instant.now().toString());
        logData.put("service", service);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logData.put("method", signature.toShortString());
        logData.put("args", joinPoint.getArgs());
        return logData;
    }

    private void logMessage(String prefix, Map<String, Object> logData) {
        try {
            log.info("{}: {}", prefix, mapper.writeValueAsString(logData));
        } catch (Exception e) {
            log.error("Failed to log message", e);
        }
    }
}
