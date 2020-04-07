package com.kind1ess.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = "com.kind1ess")
@PropertySource("classpath:databaseconnection.properties")
@Import(DatabaseConfig.class)
@EnableAspectJAutoProxy
public class AccountConfig {

}
