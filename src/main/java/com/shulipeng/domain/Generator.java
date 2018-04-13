package com.shulipeng.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * @author pengxianyang
 * @date 2018/4/4
 * @company
 * @description
 */
@Setter
@Getter
public class Generator {

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块 用于设置文件夹名称 默认是类名
     */
    private String module;

    /**
     * 作者
     */
    private String author;

    /**
     * 公司
     */
    private String company;

    /**
     * 需要去除的表前缀
     */
    private String needRemovePre;

    /**
     * 前台文件类型 可分为 html 和 jsp
     */
    private String fgFileType;

    /**
     * 前台分页还是后台分页 server client
     */
    private String sidePagination;

    /**
     * 是否需要批量删除
     */
    private Boolean batchDelete;

    /**
     * 是否需要批量插入
     */
    private Boolean batchInsert;

    /**
     * 是否需要批量更新
     */
    private Boolean batchUpdate;

    /**
     * 模糊查询
     */
    private Boolean fuzzyLookup;

    /**
     * 导出
     */
    private Boolean export;

    /**
     * 前端所需插件
     */
    private String[] fgPlugins;

    /**
     * 如果数据库是oracle，指定的序列
     */
    private String oracleSequence;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
