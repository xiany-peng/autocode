package com.shulipeng.controller;

import com.shulipeng.config.DataSourceConfig;
import com.shulipeng.utils.R;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengxianyang
 * @date 2018/4/3
 * @company QingDao Airlines
 * @description
 */
@Controller
@RequestMapping("/config")
public class ConfigController {

    private static  final Logger logger = LoggerFactory.getLogger(ConfigController.class);
    private static final String prefix = "/config";

    @Autowired
    DataSourceConfig dataSourceConfig;

    /**
     * 进入数据源页面
     * @param model
     * @return
     */
    @GetMapping("/db")
    String toDBPage(Model  model){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("db.properties");
            Map<String,Object> dbMap = new HashMap<>(16);
            dbMap.put("driverClassName",conf.getString("driverClassName"));
            dbMap.put("url",conf.getString("url"));
            dbMap.put("username",conf.getString("username"));
            dbMap.put("password",conf.getString("password"));
            model.addAttribute("db",dbMap);
        } catch (ConfigurationException e) {
            logger.error("获取数据库配置文件失败："+e);
        }
        return  prefix + "/db";
    }

    /**
     * 动态修改数据库地址，并且重新配置数据源
     * @param dbMap
     * @return
     */
    @PostMapping("/db/update")
    @ResponseBody
    R changeDB(@RequestParam Map<String,Object> dbMap){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("db.properties");
            conf.setProperty("driverClassName",dbMap.get("driverClassName"));
            conf.setProperty("url",dbMap.get("url"));
            conf.setProperty("username",dbMap.get("username"));
            conf.setProperty("password",dbMap.get("password"));
            conf.save();
            dataSourceConfig.changeDataSource();
        } catch (ConfigurationException e) {
            logger.error("获取数据库配置文件失败：" + e);
            return R.error("保存配置文件时出错" + e);
        }
        return  R.ok();
    }

    /**
     * 进入生成代码配置的页面
     * @param model
     * @return
     */
    @GetMapping("/generator")
    String toGeneratorConfPage(Model  model){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
            Map<String,Object> property = new HashMap<>(16);
            property.put("package",conf.getString("package"));
            property.put("module",conf.getString("module"));
            property.put("author",conf.getString("author"));
            property.put("company",conf.getString("company"));
            property.put("needRemovePre",conf.getString("needRemovePre"));
            property.put("bgFileType",conf.getString("bgFileType"));
            property.put("sidePagination",conf.getString("sidePagination"));
            property.put("batchRemove",conf.getString("batchRemove"));
            property.put("fuzzyLookup",conf.getString("fuzzyLookup"));
            model.addAttribute("properties",property);
        } catch (ConfigurationException e) {
            logger.error("获取数据库配置文件失败："+e);
        }
        return  prefix + "/generator";
    }

}
