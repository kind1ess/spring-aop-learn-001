package com.kind1ess.service.impl;

import com.kind1ess.model.Account;
import com.kind1ess.repository.AccountRepository;
import com.kind1ess.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAllAccount() {
        List<Account> accountList = null;
        try {
            accountList = accountRepository.findAllAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public Account findAccountById(Integer id) {
        Account account = null;
        try {
            account = accountRepository.findAccountById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public void saveAccount(Account account) {
        try {
            if (accountRepository.saveAccount(account)) {
                System.out.println("保存成功");
            } else {
                System.out.println("保存失败，账户已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {

        try {
            if (accountRepository.updateAccount(account)) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAccountById(Integer id) {
        try {
            if (accountRepository.deleteById(id)) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败，账户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        if (sourceName == targetName){
            System.out.println("不能向自己转账");
            return;
        }
        try {
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
            accountRepository.updateAccount(targetAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
