package practice.cicd.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void logInfo() {
    }

    @Around(value = "logInfo()")
    public Object printLog(ProceedingJoinPoint joinPoint) {
        final HttpServletRequest request = getRequest();
        log.info("url : {}", request.getRequestURI());
        MDC.put("traceId", UUID.randomUUID().toString());
        final String signatureName = getSignatureName(joinPoint);
        log.info(">>>>> API start [" + signatureName + "() from "
                + request.getRemoteAddr() + "] by "
                + request.getMethod() + " "
                + request.getRequestURI()
                + MDC.get("traceId"));

        final long startTime = System.currentTimeMillis();
        Object proceed = process(joinPoint, request, signatureName);
        final long timeDiff = System.currentTimeMillis() - startTime;
        log.info("시간차이(m) : {}", timeDiff);
        if (timeDiff > 1000) {
            throw new RuntimeException("문자 알림");
        }
        return proceed;
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    private String getSignatureName(final ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName();
    }

    private Object process(final ProceedingJoinPoint joinPoint, final HttpServletRequest request, final String signatureName) {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error(">>>>> controller start [" + signatureName + "() from " + request.getRemoteAddr() + "] with Error[" + e.getMessage() + "]");
            throw new RuntimeException("에러 나요.");
        }
    }
}
