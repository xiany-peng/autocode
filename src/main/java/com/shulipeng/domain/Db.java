package com.shulipeng.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author pengxianyang
 * @date 2018/4/4
 * @company QingDao Airlines
 * @description
 */
@Setter
@Getter
public class Db {

    /**
     * 驱动class
     */
    private String driverClassName;

    /**
     * 地址
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
