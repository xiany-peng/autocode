package com.shulipeng.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * @Description: 数据库的表
 * @author pengxianyang
 * @date 2018/3/25
 */
@Setter
@Getter
public class Table {

    //表名
    private String tableName;
    //注释
    private String comment;
    //主键
    private Column pk;
    //所有列
    private List<Column> columns;
    //创建时间
    private Date createTime;

    //类名
    private String className;
    //类名 首字母是小写的
    private String classNameSmall;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
