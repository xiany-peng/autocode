package com.shulipeng.domain;

import java.util.List;

/**
 * @author pengxianyang
 * @date 2018/4/4
 * @company QingDao Airlines
 * @description
 */
public class Generator {

    private String packageName;//包名

    private String module;//模块 用于设置文件夹名称 默认是类名

    private String author;//作者

    private String company;//公司

    private String needRemovePre;//需要去除的表前缀

    private String bgFileType;//前台文件类型 可分为 html 和 jsp

    private String sidePagination;//前台分页还是后台分页 server client

    private Boolean batchRemove;//是否需要批量删除

    private Boolean fuzzyLookup;//模糊查询

    private String bgPlugins;//前端所需插件

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNeedRemovePre() {
        return needRemovePre;
    }

    public void setNeedRemovePre(String needRemovePre) {
        this.needRemovePre = needRemovePre;
    }

    public String getBgFileType() {
        return bgFileType;
    }

    public void setBgFileType(String bgFileType) {
        this.bgFileType = bgFileType;
    }

    public String getSidePagination() {
        return sidePagination;
    }

    public void setSidePagination(String sidePagination) {
        this.sidePagination = sidePagination;
    }

    public Boolean getBatchRemove() {
        return batchRemove;
    }

    public void setBatchRemove(Boolean batchRemove) {
        this.batchRemove = batchRemove;
    }

    public Boolean getFuzzyLookup() {
        return fuzzyLookup;
    }

    public void setFuzzyLookup(Boolean fuzzyLookup) {
        this.fuzzyLookup = fuzzyLookup;
    }

    public String getBgPlugins() {
        return bgPlugins;
    }

    public void setBgPlugins(String bgPlugins) {
        this.bgPlugins = bgPlugins;
    }
}
