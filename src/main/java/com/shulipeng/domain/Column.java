package com.shulipeng.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author pengxianyang
 * @Description: 数据库的列
 * @date 2018/3/2522:19
 */
public class Column {

    //列名
    private String columnName;
    //列的类型
    private String dataType;
    //备注
    private String comment;
    //额外信息字段 如：auto_increment
    private String extra;
    //
    private String columnKey;

    private String attrType;
    //属性名称 首字母小写
    private String attrNameSmall;
    //属性名称 首字母大写
    private String attrName;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrNameSmall() {
        return attrNameSmall;
    }

    public void setAttrNameSmall(String attrNameSmall) {
        this.attrNameSmall = attrNameSmall;
    }
}
