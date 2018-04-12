package com.shulipeng.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.shulipeng.AutocodeApplication;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.ThreadFactory;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/4/2
 */
@Configuration
public class DataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 数据库类型
     */
    private String dbType;

    @Bean(name = "dataSource")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        try {
            PropertiesConfiguration properties = new PropertiesConfiguration("db.properties");
            String driverClassName = properties.getString("driverClassName");
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(properties.getString("url"));
            dataSource.setUsername(properties.getString("username"));
            dataSource.setPassword(properties.getString("password"));

            //判断数据库类型
            if(driverClassName.contains(Constant.DB_TYPE_MYSQL)){
                this.setDbType(Constant.DB_TYPE_MYSQL);
            }else if(driverClassName.contains(Constant.DB_TYPE_ORACLE)){
                this.setDbType(Constant.DB_TYPE_ORACLE);
            }

        } catch (ConfigurationException e) {
            logger.error("获取数据库配置文件失败" + e);
        }

        return dataSource;
    }

    /**
     * 动态修改数据库链接
     */
    public void changeDataSource () {
        Thread restartThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    AutocodeApplication.restart();
                } catch (InterruptedException ignored) {
                }
            }
        });
        restartThread.setDaemon(false);
        restartThread.start();
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType){
        this.dbType = dbType;
    }
}
