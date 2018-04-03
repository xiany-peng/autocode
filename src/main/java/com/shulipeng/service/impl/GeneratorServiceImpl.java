package com.shulipeng.service.impl;

import com.shulipeng.config.Constant;
import com.shulipeng.config.DataSourceConfig;
import com.shulipeng.dao.BaseTableMapper;
import com.shulipeng.dao.MysqlTableMapper;
import com.shulipeng.dao.OracleTableMapper;
import com.shulipeng.domain.Column;
import com.shulipeng.domain.Table;
import com.shulipeng.service.GeneratorService;
import com.shulipeng.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/3/25
 */
@Service
public class GeneratorServiceImpl implements GeneratorService{

    private BaseTableMapper tableMapper;

    @Autowired
    DataSourceConfig dataSourceConfig;
    @Autowired
    OracleTableMapper oracleTableMapper;
    @Autowired
    MysqlTableMapper mysqlTableMapper;

    @PostConstruct
    public void setTableMapper(){
        if(Constant.DB_TYPE_MYSQL.equals(dataSourceConfig.getDbType())){
            this.tableMapper = mysqlTableMapper;
        }else if(Constant.DB_TYPE_ORACLE.equals(dataSourceConfig.getDbType())){
            this.tableMapper = oracleTableMapper;
        }
    }

    @Override
    public List<Table> list() {
        return tableMapper.list();
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for(String tableName : tableNames){

            //查询表信息
            Table table = tableMapper.get(tableName);
            //查询列信息
            List<Column> columns = tableMapper.listColumns(tableName);

            //生成代码
            GenUtils.generatorCode(table, columns,dataSourceConfig.getDbType(), zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
