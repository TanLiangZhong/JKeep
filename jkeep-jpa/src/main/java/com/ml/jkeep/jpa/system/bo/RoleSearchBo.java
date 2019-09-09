package com.ml.jkeep.jpa.system.bo;

import lombok.Data;

/**
 * 角色搜索 - bo
 *
 * @author liangzhong
 * @date 2019-9-9 16:50:53
 */
@Data
public class RoleSearchBo {

    /**
     * 关键字 , (操作人, 请求地址, 请求方法)
     */
    private String keyword;

}
