package com.ml.jkeep.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统模块枚举
 *
 * @author 谭良忠
 * @date 2019/7/23 11:32
 */
public interface SysEnums {

    @Getter
    @AllArgsConstructor
    enum RoleLinkType {
        /**
         * 角色关联关系类型
         */
        MENU((byte) 0, "菜单"),
        ELEMENT((byte) 1, "页面元素");

        private Byte code;
        private String name;
    }

}
