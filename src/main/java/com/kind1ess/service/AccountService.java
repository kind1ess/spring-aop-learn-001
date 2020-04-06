package com.kind1ess.service;


import com.kind1ess.model.Account;

import java.util.List;

/**
 * 账户业务层接口
 */
public interface AccountService {

    /**
     * 查询所有账户
     *
     * @return
     */
    public List<Account> findAllAccount() throws Exception;

    /**
     * 根据id查询账户
     *
     * @param id
     * @return
     */
    public Account findAccountById(Integer id) throws Exception;

    /**
     * 保存账户
     *
     * @param account
     */
    public void saveAccount(Account account) throws Exception;

    /**
     * 更新账户
     *
     * @param account
     */
    public void updateAccount(Account account) throws Exception;

    /**
     * 删除账户
     *
     * @param id
     */
    public void deleteAccountById(Integer id) throws Exception;

    /**
     * 转账
     *
     * @param sourceName
     * @param targetName
     * @param money
     */
    public void transfer(String sourceName, String targetName, Float money) throws Exception;
}
