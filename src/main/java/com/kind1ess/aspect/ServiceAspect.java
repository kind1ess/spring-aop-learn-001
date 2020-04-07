package com.kind1ess.aspect;

import com.kind1ess.utils.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component("serviceAspect")
public class ServiceAspect {

    @Pointcut("execution(* com.kind1ess.service.impl.AccountServiceImpl.*(..))")
    public void pointCut(){
    }

    @Autowired
    private TransactionManager transactionManager;


    @Around("pointCut()")
    public Object controlTransaction(ProceedingJoinPoint joinPoint){
        Object returnValue = null;
        try {
            Object[] args = joinPoint.getArgs();
            transactionManager.beginTransaction();
            returnValue = joinPoint.proceed(args);
            transactionManager.commit();
            return returnValue;
        } catch (Throwable throwable) {
            transactionManager.rollBack();
            throw new RuntimeException(throwable);
        } finally {
            transactionManager.close();
        }
    }

}
