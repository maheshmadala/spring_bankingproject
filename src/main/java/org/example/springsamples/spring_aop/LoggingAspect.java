package org.example.springsamples.spring_aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* org.example.springsamples.service.PaymentService.*(..))")
    public void before(JoinPoint jp) {
        String id = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        System.out.println("Logging Before Payment Method Execution. MethodId: "
                + id + " args: " + Arrays.asList(args));
    }
    @After("execution(* org.example.springsamples.service.PaymentService.*(..))")
    public void after(JoinPoint jp){
        String id = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        System.out.println("Logging After Payment Method Execution. MethodId: "
                + id + " args: " + Arrays.asList(args));
    }

    @Around("execution(* org.example.springsamples.service.PaymentService.*(..))")
    public Object around(ProceedingJoinPoint jp) throws Throwable {

        String id  = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        System.out.println("Logging Around (Before) Payment Method. MethodId: "
                + id + " args: " + Arrays.asList(args));

        long startTime = System.currentTimeMillis();

        Object result = jp.proceed();

        long endTime = System.currentTimeMillis();

        System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        System.out.println("Logging Around (After) Payment Method. MethodId: "
                + id + " args: " + Arrays.asList(args) + " Result: " + result);

        return result;
    }
}