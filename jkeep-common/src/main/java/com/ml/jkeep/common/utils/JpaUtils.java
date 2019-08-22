package com.ml.jkeep.common.utils;

/**
 * jpa 工具类
 *
 * @author 谭良忠
 * @date 2019/8/22 15:34
 */
public class JpaUtils {

    /**
     * 转换参数 , 前后添加通配符
     *
     * @param param 参数
     * @return "%" + param + "%"
     */
    public static String addWildcard(String param) {
        return param == null ? null : "%" + param + "%";
    }

}
