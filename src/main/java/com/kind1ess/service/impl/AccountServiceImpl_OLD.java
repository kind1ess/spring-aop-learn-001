package com.kind1ess.service.impl;

import com.kind1ess.model.Account;
import com.kind1ess.repository.AccountRepository;
import com.kind1ess.service.AccountService;
import com.kind1ess.utils.TransactionManager;

import java.util.List;

public class AccountServiceImpl_OLD implements AccountService {


    private AccountRepository accountRepository;
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAllAccount() {
        List<Account> accountList = null;
        try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            accountList = accountRepository.findAllAccount();
            //3.提交事务
            transactionManager.commit();
            //4.返回结果
            return accountList;
        } catch (Exception e) {
            //回滚操作
            transactionManager.rollBack();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            transactionManager.close();
        }
    }

    public Account findAccountById(Integer id) {
        Account account = null;
        try {
            transactionManager.beginTransaction();
            account = accountRepository.findAccountById(id);
            transactionManager.commit();
            return account;
        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException(e);
        }
        finally {
            transactionManager.close();
        }
    }

    public void saveAccount(Account account) {
        try {
            transactionManager.beginTransaction();
            if (accountRepository.saveAccount(account)) {
                transactionManager.commit();
                System.out.println("保存成功");
            } else {
                System.out.println("保存失败，账户已存在");
            }
        } catch (Exception e) {
            transactionManager.rollBack();
            e.printStackTrace();
        }finally {
            transactionManager.close();
        }
    }

    public void updateAccount(Account account) {

        try {
            transactionManager.beginTransaction();
            if (accountRepository.updateAccount(account)) {
                transactionManager.commit();
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (Exception e) {
            transactionManager.rollBack();
            e.printStackTrace();
        }finally {
            transactionManager.close();
        }
    }

    public void deleteAccountById(Integer id) {
        try {transactionManager.beginTransaction();
            if (accountRepository.deleteById(id)) {
                transactionManager.commit();
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败，账户不存在");
            }
        } catch (Exception e) {
            transactionManager.rollBack();
            e.printStackTrace();
        }finally {
            transactionManager.close();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        if (sourceName == targetName){
            System.out.println("不能向自己转账");
            return;
        }
        try {
            transactionManager.beginTransaction();
            Account sourceAccount = accountRepository.findByName(sourceName);
            Account targetAccount = accountRepository.findByName(targetName);
            Float sourceMoney = sourceAccount.getMoney();
            Float targetMoney = targetAccount.getMoney();
            if (sourceMoney >= money) {
                sourceMoney -= money;
                targetMoney += money;
            }
            else {
                System.out.println("账户余额不足，转账失败");
            }
            sourceAccount.setMoney(sourceMoney);
            targetAccount.setMoney(targetMoney);
            accountRepository.updateAccount(sourceAccount);
            int i =1/0;
            accountRepository.updateAccount(targetAccount);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollBack();
            e.printStackTrace();
        }finally {
            transactionManager.close();
        }
    }
}
