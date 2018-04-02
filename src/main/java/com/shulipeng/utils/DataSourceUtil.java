package com.shulipeng.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.shulipeng.config.ApplicationContextRegister;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.security.krb5.Config;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/4/2
 */
public class DataSourceUtil {

    /**
     * Updated at 上午11:49:26, on 2008-12-3<br>
     * 通过配置文件获取联网方式和数据源的配置信息
     *
     * @author Guokai
     */
    public static void initSubmitTypeAndConnectionType () {

        try {
            Configuration properties = new PropertiesConfiguration("db.properties");
            System.out.println("driverClassName" + properties.getString("driverClassName"));
            System.out.println("url" + properties.getString("url"));
            System.out.println("username" + properties.getString("username"));
            System.out.println("password" + properties.getString("password"));

            // 根据数据库连接配置来设置数据源对象
            DruidDataSource basicDS = (DruidDataSource) ApplicationContextRegister.getBean(DataSource.class);
            // 这里需要先关闭数据源，才可以使新的数据源设置生效
            basicDS.close();
            basicDS.setDriverClassName(properties.getString("driverClassName"));
            basicDS.setUrl(properties.getString("url"));
            basicDS.setUsername(properties.getString("username"));
            basicDS.setPassword(properties.getString("password"));
            basicDS.init();
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败:",e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
