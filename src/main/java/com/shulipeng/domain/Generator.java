package com.shulipeng.domain;

import lombok.Getter;
import lombok.Setter;


/**
 * @author pengxianyang
 * @date 2018/4/4
 * @company
 * @description
 */
@Setter
@Getter
public class Generator {

    private String packageName;//包名

    private String module;//模块 用于设置文件夹名称 默认是类名

    private String author;//作者

    private String company;//公司

    private String needRemovePre;//需要去除的表前缀

    private String fgFileType;//前台文件类型 可分为 html 和 jsp

    private String sidePagination;//前台分页还是后台分页 server client

    private Boolean batchRemove;//是否需要批量删除

    private Boolean fuzzyLookup;//模糊查询

    private String[] fgPlugins;//前端所需插件

    private String oracleSequence;//如果数据库是oracle，指定的序列
}
