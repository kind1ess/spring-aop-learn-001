package com.kind1ess.utils;

import java.sql.SQLException;

/**
 * 事务管理的工具类，包含了开启事务，提交事务，回滚事务和释放连接
 */
public class TransactionManager {

    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void beginTransaction(){
        try {
            connectionUtil.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit(){
        try {
            connectionUtil.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollBack(){
        try {
            connectionUtil.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            connectionUtil.getThreadConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
