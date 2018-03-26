package com.shulipeng;

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
    private String db;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
