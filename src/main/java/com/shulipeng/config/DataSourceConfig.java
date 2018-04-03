package com.shulipeng.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/4/2
 */
@Component
public class DataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    public static String dbType = "";

    @Bean(name = "dataSource")
    @Primary
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        try {
            PropertiesConfiguration properties = new PropertiesConfiguration("db.properties");
            dataSource.setDriverClassName(properties.getString("driverClassName"));
            dataSource.setUrl(properties.getString("url"));
            dataSource.setUsername(properties.getString("username"));
            dataSource.setPassword(properties.getString("password"));
            dbType = "mysql";
        } catch (ConfigurationException e) {
            logger.error("获取数据库配置文件失败");
        }

        return dataSource;
    }
}
