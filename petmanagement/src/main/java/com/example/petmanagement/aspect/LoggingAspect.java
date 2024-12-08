package com.example.petmanagement.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.petmanagement.service.impl.HouseholdServiceImpl.*(..))")
    public void householdServiceMethods() {}

    @Before("householdServiceMethods()")
    public void logBefore() {
        System.out.println("Executing method...");
    }

    @After("householdServiceMethods()")
    public void logAfter() {
        System.out.println("Method executed.");
    }
}
