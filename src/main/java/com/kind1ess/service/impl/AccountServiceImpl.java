package com.kind1ess.service.impl;

import com.kind1ess.model.Account;
import com.kind1ess.repository.AccountRepository;
import com.kind1ess.service.AccountService;
import com.kind1ess.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAllAccount() throws Exception {
        return accountRepository.findAllAccount();
    }

    public Account findAccountById(Integer id) throws Exception {
        return accountRepository.findAccountById(id);
    }

    public void saveAccount(Account account) throws Exception {
        accountRepository.saveAccount(account);
    }

    public void updateAccount(Account account) throws Exception {
        accountRepository.updateAccount(account);
    }

    public void deleteAccountById(Integer id) throws Exception {
        accountRepository.deleteById(id);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) throws Exception {
        System.out.println("transfer...");
        if (sourceName.equals(targetName)){
            System.out.println("不能向自己转账");
            return;
        }
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
//            int i =1/0;
            accountRepository.updateAccount(targetAccount);
    }
}
