package com.kind1ess.factory;

import com.kind1ess.model.Account;
import com.kind1ess.service.AccountService;
import com.kind1ess.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
@Deprecated
public class BeanFactory {

    private AccountService accountService;

    private TransactionManager transactionManager;

    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public AccountService getAccountService() {
        return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces()
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue = null;
                        try {
                            transactionManager.beginTransaction();
                            returnValue = method.invoke(accountService,args);
                            transactionManager.commit();
                            return returnValue;
                        } catch (Exception e) {
                            transactionManager.rollBack();
                            throw new Exception(e);
                        } finally {
                            transactionManager.close();
                        }
                    }
                });
    }
}
