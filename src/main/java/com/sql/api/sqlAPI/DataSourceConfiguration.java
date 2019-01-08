package com.sql.api.sqlAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.service.relational.BasicDbcpPooledDataSourceCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {



    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.seconddatasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "firstdb")
    @Primary
    @ConfigurationProperties("app.datasource.configuration")
    public DataSource firstdb() {
        return firstDataSourceProperties().initializeDataSourceBuilder()
                .build();

    }

    @Bean(name = "seconddb")
    @ConfigurationProperties("app.seconddatasource.configuration")
    public DataSource seconddb() {
        return secondDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name="tm1")
    @Autowired
    @Primary
    DataSourceTransactionManager tm1(@Qualifier("firstdb") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
        return txm;
    }

    @Bean(name="tm2")
    @Autowired
    DataSourceTransactionManager tm2(@Qualifier ("seconddb") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
        return txm;
    }
}
