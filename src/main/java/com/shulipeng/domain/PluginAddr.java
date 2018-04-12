package com.shulipeng.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author pengxianyang
 * @date 2018/4/4
 * @company QingDao Airlines
 * @description 前端控件地址
 */

@Setter
@Getter
public class PluginAddr {

    private String jquery;

    private String bootstrapPrefix;

    private String bootstraptablePrefix;

    private String datetimepickerPrefix;

    private String bootstrapValidatorPrefix;

    private String layerPrefix;

    private String select2Prefix;

    private String uploadfivePrefix;

    private String ztreePrefix;

    private String hplusPrefix;

    private String fontAwesomePrefix;

    private String tableExportPrefix;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
