package com.ml.jkeep.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 逻辑删除标记枚举类
 *
 * @author 谭良忠
 * @date 2019/8/6 13:47
 */
@Getter
@AllArgsConstructor
public enum DFlagEnum {

    /**
     * 删除标记
     */
    NORMAL((byte) 0, "未删除"),
    DELETE((byte) 1, "已删除");

    private Byte code;
    private String name;

}
