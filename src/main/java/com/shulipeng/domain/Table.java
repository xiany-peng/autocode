package com.shulipeng.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * @Description: 数据库的表
 * @author pengxianyang
 * @date 2018/3/25
 */
public class Table {

    //表名
    private String tableName;
    //注释
    private String comment;
    //主键
    private Column pk;
    //除主键外其他列
    private List<Column> columns;
    //创建时间
    private Date createTime;

    //类名
    private String className;
    //类名 首字母是大写的
    private String classNameBig;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Column getPk() {
        return pk;
    }

    public void setPk(Column pk) {
        this.pk = pk;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNameBig() {
        return classNameBig;
    }

    public void setClassNameBig(String classNameBig) {
        this.classNameBig = classNameBig;
    }
}
