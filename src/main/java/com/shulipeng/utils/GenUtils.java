package com.shulipeng.utils;

import com.shulipeng.domain.Column;
import com.shulipeng.domain.Table;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author pengxianyang
 * @Description: 生成代码
 * @date 2018/3/27
 */
public class GenUtils {

    /**
     * 生成代码
     * @param table
     * @param columns
     * @param zip
     */
    public static void generatorCode(Table table, List<Column> columns, ZipOutputStream zip) {

        //1.获取配置信息
        Configuration conf = getConfig();
        //2.表和列类属性填充
        fillTableClassAttr(table,conf.getString("needRemovePre"));

        //2.加载模板
        //3.创建 velocity 实例
        //4.给实例添加context
        //5.完成
    }

    /**
     * 获取配置文件
     * @return
     */
    private static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败:",e);
        }
    }

    /**
     * 给表格类添加属性
     * @param table
     * @param needRemovePre
     */
    private static void fillTableClassAttr(Table table, String needRemovePre) {
        String className = table.getTableName();
        if(StringUtils.isNoneBlank(needRemovePre) && className.substring(0,needRemovePre.length()).equals(needRemovePre)){
            className = className.substring(0,needRemovePre.length());
        }

        String className =
    }

    public static String toJava(String )
}
