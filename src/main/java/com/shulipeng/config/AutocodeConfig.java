package com.shulipeng.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/3/25
 */
@Component
@ConfigurationProperties(prefix = "autocode")
public class AutocodeConfig {

    //数据库类型
    private String dbType;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
