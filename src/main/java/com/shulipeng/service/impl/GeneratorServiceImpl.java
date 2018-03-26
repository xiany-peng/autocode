package com.shulipeng.service.impl;

import com.shulipeng.AutocodeConfig;
import com.shulipeng.dao.BaseTableMapper;
import com.shulipeng.dao.MysqlTableMapper;
import com.shulipeng.dao.OracleTableMapper;
import com.shulipeng.domain.Column;
import com.shulipeng.domain.Table;
import com.shulipeng.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/3/25
 */
@Service
public class GeneratorServiceImpl implements GeneratorService{

    private BaseTableMapper tableMapper;

    @Autowired
    AutocodeConfig autocodeConfig;
    @Autowired
    OracleTableMapper oracleTableMapper;
    @Autowired
    MysqlTableMapper mysqlTableMapper;

    @PostConstruct
    public void setTableMapper(){
        if("MYSQL".equals(autocodeConfig.getDb())){
            this.tableMapper = mysqlTableMapper;
        }else if("ORACLE".equals(autocodeConfig.getDb())){
            this.tableMapper = oracleTableMapper;
        }
    }



    @Override
    public List<Table> list() {
        return tableMapper.list();
    }

    @Override
    public Table get(String tableName) {
        return tableMapper.get(tableName);
    }

    @Override
    public List<Column> listColumns(String tableName) {
        return tableMapper.listColumns(tableName);
    }
}
