package com.shulipeng.dao;

import com.shulipeng.domain.Column;
import com.shulipeng.domain.Table;

import java.util.List;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/3/25
 */
public interface BaseTableMapper {

    /**
     * 查询所有数据库表
     * @return
     */
    List<Table> list();

    /**
     * 通过表名查询
     * @param tableName
     * @return
     */
    Table get(String tableName);

    /**
     * 查询一个表所有的列
     * @param tableName
     * @return
     */
    List<Column> listColumns(String tableName);
}
