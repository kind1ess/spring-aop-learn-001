package com.kind1ess.repository.impl;

import com.kind1ess.model.Account;
import com.kind1ess.repository.AccountRepository;
import com.kind1ess.utils.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.List;


public class AccountRepositoryImpl implements AccountRepository {

    private QueryRunner queryRunner;
    private ConnectionUtil connectionUtil;

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public List<Account> findAllAccount() throws Exception {
        return queryRunner.query(connectionUtil.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
    }

    @Override
    public Account findAccountById(Integer id) throws Exception {
        return queryRunner.query(connectionUtil.getThreadConnection(),"select * from account where id=?",new BeanHandler<Account>(Account.class),id);
    }

    @Override
    public boolean saveAccount(Account account) throws Exception {
       if (queryRunner.update(connectionUtil.getThreadConnection(),"insert into account(name,money) values(?,?)",account.getName(),account.getMoney())>0)
           return true;
       return false;
    }

    @Override
    public boolean updateAccount(Account account) throws Exception {
        if (queryRunner.update(connectionUtil.getThreadConnection(),"update account set name=?,money=? where id=?",account.getName()
                ,account.getMoney(),account.getId())>0)
            return true;
        return false;
    }

    @Override
    public boolean deleteById(Integer id) throws Exception {
        if (queryRunner.update(connectionUtil.getThreadConnection(),"delete from account where id=?",id)>0)
            return true;
        return false;
    }

    @Override
    public Account findByName(String name) throws Exception{
        List<Account> accountList = queryRunner.query(connectionUtil.getThreadConnection(),"select * from account where name=?",
                new BeanListHandler<Account>(Account.class),name);
        if (accountList == null||accountList.size()==0){
            return null;
        }
        else if(accountList.size()>1){
            throw new Exception("存在多个账户");
        }
        else
            return accountList.get(0);
    }
}
