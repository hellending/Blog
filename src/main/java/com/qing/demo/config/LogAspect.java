package com.qing.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Aspect
@Component
public class LogAspect {
    public String getTime(){
        Date date = new Date();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return sp.format(date);
    }
    @Pointcut("execution(public * com.qing.demo.Controller.*.*(..))")
    public void LogAspect(){
        System.out.println("awerw");
    }
    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("Before "+getTime()+" call "+joinPoint.toString());
    }
   @After("LogAspect()")
    public void doAfter(JoinPoint joinPoint){
       System.out.println("After "+getTime()+" call "+joinPoint.toString());
    }
    @AfterReturning("LogAspect()")
    public void doAfterReturning(JoinPoint joinPoint){
        System.out.println("After Returning "+getTime()+" call "+joinPoint.toString());
    }
    @AfterThrowing("LogAspect()")
    public void doAfterThrowing(JoinPoint joinPoint){
        System.out.println("Throwing "+getTime()+" call "+joinPoint.toString());
    }
//    @Around("LogAspect()")
//    public void doAround(JoinPoint joinPoint){
//        System.out.println("Around");
//    }
}
