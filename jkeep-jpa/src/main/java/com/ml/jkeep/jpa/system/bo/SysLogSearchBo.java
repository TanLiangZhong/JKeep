package com.ml.jkeep.jpa.system.bo;

import lombok.Data;

/**
 * 系统日志搜索 - bo
 */
@Data
public class SysLogSearchBo {

    /**
     * 操作人
     */
    private String username;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;

}
