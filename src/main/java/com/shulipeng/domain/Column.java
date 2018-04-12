package com.shulipeng.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author pengxianyang
 * @Description: 数据库的列
 * @date 2018/3/2522:19
 */
@Setter
@Getter
public class Column {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列的类型
     */
    private String dataType;

    /**
     * 备注
     */
    private String comment;

    /**
     * 额外信息字段 如：auto_increment
     */
    private String extra;

    /**
     * 列的键 如果是 PRI 则为主键
     */
    private String columnKey;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 属性名称 首字母小写
     */
    private String attrNameSmall;

    /**
     * 属性名称 首字母大写
     */
    private String attrName;

    /**
     * 是否是日期类型
     */
    private Integer isDateType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
