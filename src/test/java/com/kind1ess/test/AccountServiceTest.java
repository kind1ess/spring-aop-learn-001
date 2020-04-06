package com.kind1ess.test;

import com.kind1ess.model.Account;
import com.kind1ess.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    @Qualifier("proxyAccountService")
    private AccountService accountService;

    @Test
    public void testTransfer(){
        try {
            accountService.transfer("ccc","bbb",500.0f);
            List<Account> accountList = accountService.findAllAccount();
            for (Account account : accountList) {
                System.out.println(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
