package com.shulipeng.controller;

import com.alibaba.fastjson.JSON;
import com.shulipeng.domain.Table;
import com.shulipeng.service.GeneratorService;
import com.shulipeng.utils.DataSourceUtil;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/3/25
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {

    private static final String prefix = "/generator";

    @Autowired
    GeneratorService generatorService;

    @GetMapping("")
    String generator(){
        return prefix + "/generator";
    }

    @PostMapping("/list")
    @ResponseBody
    List<Table> list(){
        return generatorService.list();
    }

    /**
     * 单个生成代码
     * @param request
     * @param response
     * @param tableName
     */
    @GetMapping("/code/{tableName}")
    void code(HttpServletRequest request, HttpServletResponse response,
              @PathVariable("tableName") String tableName) throws IOException {
        String[] tableNames = new String[] { tableName };
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"autocode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 批量生成代码
     * @param request
     * @param response
     * @param tables
     * @throws IOException
     */
    @GetMapping("/batchCode")
    void batchCode(HttpServletRequest request, HttpServletResponse response,
                   String tables) throws IOException {
        String[] tableNames = new String[] {};
        tableNames = JSON.parseArray(tables).toArray(tableNames);
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"bootdo.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 批量生成代码
     * @param request
     * @param response
     * @param tables
     * @throws IOException
     */
    @GetMapping("/changeDataSource")
    void batchCode(){
        DataSourceUtil.initSubmitTypeAndConnectionType();
    }
}
