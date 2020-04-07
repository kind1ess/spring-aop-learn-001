package com.kind1ess.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，用于从数据源中获取链接，并且实现和线程的绑定
 */

@Component
public class ConnectionUtil {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程的连接
     *
     * @return
     */
    public Connection getThreadConnection() throws SQLException {
        //先从ThreadLocal上取连接
        Connection connection = threadLocal.get();
        //判断当前线程上是否有连接
        if (connection == null) {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public void removeConnection(){
        threadLocal.remove();
    }
}
