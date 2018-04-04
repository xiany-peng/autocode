package com.shulipeng.controller;

import com.shulipeng.config.DataSourceConfig;
import com.shulipeng.domain.BgAddr;
import com.shulipeng.domain.Db;
import com.shulipeng.domain.Generator;
import com.shulipeng.utils.BeanUtils;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
     * 进入配置数据源页面
     * @param model
     * @return
     */
    @GetMapping("/db")
    String toDBPage(Model  model){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("db.properties");
            Db db = BeanUtils.propertyToBean(Db.class,conf);
            model.addAttribute("db",db);
        } catch (ConfigurationException e) {
            logger.error("获取数据库配置文件失败："+e);
        }
        return  prefix + "/db";
    }

    /**
     * 动态修改数据库地址，并且重新启动项目
     * @param db
     * @return
     */
    @PostMapping("/db/update")
    @ResponseBody
    R changeDB(Db db){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("db.properties");
            BeanUtils.beanToProperty(db,conf);
            conf.save();
            dataSourceConfig.changeDataSource();
        } catch (ConfigurationException e) {
            logger.error("获取数据库配置文件失败：" + e);
            return R.error("保存配置文件时出错" + e);
        }
        return  R.ok();
    }

    /**
     * 进入前端插件地址的页面
     * @param model
     * @return
     */
    @GetMapping("/bg")
    String toBgAddrPage(Model  model){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("bg.properties");
            BgAddr bgAddr = BeanUtils.propertyToBean(BgAddr.class,conf);
            model.addAttribute("bg",bgAddr);
        } catch (ConfigurationException e) {
            logger.error("获取前端地址配置文件失败：" + e);
        }
        return  prefix + "/bg";
    }


    /**
     * 保存前端地址
     * @param bgAddr
     * @return
     */
    @PostMapping("/bg/update")
    @ResponseBody
    R changeBgAddr(BgAddr bgAddr){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("bg.properties");
            BeanUtils.beanToProperty(bgAddr,conf);
            conf.save();
        } catch (ConfigurationException e) {
            logger.error("获取前端地址配置文件失败：" + e);
            return R.error("获取前端地址配置文件失败：" + e);
        }
        return  R.ok();
    }

    /**
     * 进入生成配置的页面
     * @param model
     * @return
     */
    @GetMapping("/generator")
    String toGeneratorConfPage(Model  model){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
            Generator generator =  BeanUtils.propertyToBean(Generator.class,conf);
            model.addAttribute("generator",generator);

            String[] bgAllPlugins =  conf.getStringArray("bgAllPlugins");
            model.addAttribute("bgAllPlugins",bgAllPlugins);
        } catch (ConfigurationException e) {
            logger.error("获取 生成配置 文件失败："+e);
        }
        return  prefix + "/config";
    }

    /**
     * 保存生成配置
     * @param bgAddr
     * @return
     */
    @PostMapping("/generator/update")
    @ResponseBody
    R changeGenerator(Generator generator){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("bg.properties");
            BeanUtils.beanToProperty(generator,conf);
            conf.save();
        } catch (ConfigurationException e) {
            logger.error("获取 生成配置 文件失败：" + e);
            return R.error("获取 生成配置 文件失败：" + e);
        }
        return  R.ok();
    }

}
