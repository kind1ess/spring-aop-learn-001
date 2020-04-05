package com.kind1ess.repository;

import com.kind1ess.model.Account;

import java.util.List;

public interface AccountRepository {
    /**
     * 查询所有
     */
    public List<Account> findAllAccount() throws Exception;

    /**
     * 查询一个
     * @param id
     * @return
     */
    public Account findAccountById(Integer id) throws Exception;

    /**
     * 增加账户
     * @param account
     * @return
     */
    public boolean saveAccount(Account account) throws Exception;

    /**
     * 更新账户
     * @param account
     * @return
     */
    public boolean updateAccount(Account account) throws Exception;

    /**
     * 删除账户
     * @param id
     * @return
     */
    public boolean deleteById(Integer id) throws Exception;

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    Account findByName(String name) throws Exception;
}
