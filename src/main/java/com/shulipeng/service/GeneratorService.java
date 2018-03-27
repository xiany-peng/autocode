package com.shulipeng.service;

import com.shulipeng.domain.Column;
import com.shulipeng.domain.Table;

import java.util.List;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/3/25
 */
public interface GeneratorService {
    /**
     * 查询所有数据库表
     * @return
     */
    List<Table> list();

    /**
     * 生成代码
     * @param tableNames
     * @return
     */
    byte[] generatorCode(String[] tableNames);
}
